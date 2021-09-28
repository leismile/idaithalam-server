package ch.inss.virtualan.idaiserver.service;

import ch.inss.virtualan.idaiserver.model.Report;
import ch.inss.virtualan.idaiserver.model.Testidlist;
import ch.inss.virtualan.idaiserver.utils.FileManagement;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.virtualan.idaithalam.contract.IdaithalamExecutor;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * The type Test service.
 */
@Service
public class TestService {

  /**
   * The constant ALLTESTS.
   */
  public static final String ALLTESTS = "alltests.json";
  /**
   * The constant LASTTEST.
   */
  public static final String LASTTEST = "lasttest.json";
  private static final Logger logger = LoggerFactory.getLogger(TestService.class);
  private static final String FEATUREX = "feature/virtualan-contract.";     // 0.feature";
  private static final String DOTFEATURE = ".feature";
  private static final String REPORTOVERVIEW = "cucumber-html-reports/overview-features.html";

  /**
   * Folder to store the Cucumber reports.
   */
  @Value("${ch.inss.idaiserver.reports.folder}")
  private String storagePath;
  @Value("${ch.inss.idaiserver.host}")
  private String serverHost;


  /**
   * /test PUT Cucumblan service is initialized when calling this method (init()).
   *
   * @param cucumblan the cucumblan
   * @param testid    the testid
   * @return report report
   */
  public Report runTest(String userId, Cucumblan cucumblan, UUID testid) {
    //TODO check userId
    logger.debug(
        "Running tests from folder: " + this.addSlash(this.storagePath) + cucumblan.getFolder());

    if (cucumblan.getUuid() == null) {
      logger.error("Object for cucumblan service not correctly initiazlized.");
    }
    /* Paths for the results in the filesystem and URLs. */
    final String reportFolder = this.storagePath + File.separator + cucumblan.getFolder();
    final String skip = ".*=IGNORE";
    final String skipProp = reportFolder + File.separator + "exclude-response.properties";
    final String lastSession = reportFolder + File.separator + LASTTEST;
    final String allSessions = reportFolder + File.separator + ALLTESTS;

    /* Initialize cucumblan service with last session. */
    Report links = null;
    try {
      String json = FileManagement.readToString(lastSession);
      links = PersistJSON.reportFromJSON(json);
      links.setSessionNr((links.getSessionNr() + 1));
      cucumblan.setSessionNr(links.getSessionNr());

      if (!new File(reportFolder + File.separator + cucumblan.getSessionNr()).exists()) {
        new File(reportFolder + File.separator + cucumblan.getSessionNr()).mkdir();
      }
      final String propsUrl = this.serverHost + File.separator + cucumblan.getFolder();
      final String reportURL =
          this.serverHost + File.separator + cucumblan.getFolder() + File.separator + cucumblan
              .getSessionNr();
      logger.debug("Cucumblan: " + File.separator + cucumblan.toString());
      logger.debug("Report from last session: " + File.separator + links.toString());

      /* Initialize response. */
      links.setError(FileManagement.NOERROR);
      //	    links.setCreationTime(FileManagement.whatTime());
      //	    links.setSkipResponseValidation(cucumblan.getSkipResponseValidation()); TODO

      final String testId = cucumblan.getUuid().toString();
      links.setTestId(cucumblan.getUuid());

      long startTime = System.nanoTime();
      links.setStartTime(FileManagement.whatTime());

      /* Here comes the actual man Maven test execution.
       **/
      String result = this.mvnTest(reportFolder, cucumblan.getSessionNr());

      /* Calcuate execution time. */
      long endTime = System.nanoTime();
      long duration = (endTime - startTime);
      Duration d = Duration.ofNanos(duration);
      links.setDurationSeconds(Long.valueOf(d.getSeconds()));
      links.setEndTime(FileManagement.whatTime());

      /* Check test result. */
      boolean isSuccess = Boolean.valueOf(result);
      links.setSuccess(isSuccess);
      if (isSuccess == false && result.equalsIgnoreCase("false") == false) {
        links.setError(result);
        links.setTestExecuted(false);
        links.setMessage("An internal error occured.");
        return links;
      } else {
        links.setTestExecuted(true);
        links.setMessage("Test executed.");
      }

      /* First get all previous sessions. Add current session. */
      List<Report> list = PersistJSON.readReports(allSessions);
      list.add(links);
      logger.debug("There are " + list.size() + " test sessions stored.");

      String target = reportURL + File.separator + FEATUREX + "0" + DOTFEATURE;
      links.setLinkToFeature(target);

      /* Copy the cucumber report folder and send back the link
       * to the main report html file: report-feature_1959214294.html.
       * */
      String linkReport = reportURL + File.separator + REPORTOVERVIEW;
      logger.info("Generated report html file: " + linkReport);
      links.setLinkToReport(linkReport);
      links.setLinkToProperties(propsUrl + File.separator + FileManagement.PROPERTIES);
      links.setLinkToSessions(propsUrl + File.separator + ALLTESTS);

      /* Persist test execution in JSON file. */
      PersistJSON.writeJSON(reportFolder + File.separator + LASTTEST, links);
      String reportList = PersistJSON.writeArray(list, allSessions);
      FileManagement.writeString(allSessions, reportList);


    } catch (JsonProcessingException e) {
      e.printStackTrace();
      if (links == null) {
        links = new Report();
      }
      links.setError(e.getLocalizedMessage());
      links.setMessage("An internal error occured.");
      links.setTestExecuted(false);
    } catch (IOException ioe) {
      if (links == null) {
        links = new Report();
      }
      ioe.printStackTrace();
      links.setError(ioe.getLocalizedMessage());
      links.setMessage("An internal error occured.");
      links.setTestExecuted(false);
    }

    return links;
  }

  public Report doInitialTest(String userId, MultipartFile filestream, String serverurl, String workspace, String testrun, String staging, String version, String dataType, String execute, String skipResponseValidation) {
    Cucumblan cucumblan = null;
    try {
      String dataload = filestream.getOriginalFilename();
      Boolean e = new Boolean(true);
      if ( execute != null) {
        e = new Boolean(execute);
      }
      Boolean skip = new Boolean(false);
      if ( skipResponseValidation != null) {
        skip = new Boolean(skipResponseValidation);
      }
  
      cucumblan = new Cucumblan(userId,workspace, staging, version, false);
      cucumblan.init();
      cucumblan.addFILE(dataload);
      cucumblan.setUploadFilename(dataload);
      cucumblan.setSkipResponseValidation(skip);
      cucumblan.setTYPE(dataType);
      cucumblan.generateIdAndFolder();
      cucumblan.setInputStream(filestream.getInputStream());
      cucumblan.addURL(null,serverurl);
      cucumblan.setExecute(e);
      } catch (IOException e1) {
        logger.error("The uploaded file is not readable.");
        Report report = new Report();
        report.setError("The uploaded file is not readable.");
        return report;
      } catch(Exception ex){
        logger.error("Server error.", ex);
        Report report = new Report();
        report.setError("A server error occured: " + ex.getLocalizedMessage());
        return report;
    }
      return this.doInitialTest(cucumblan);
  }

  /**
   * Do initial test report (POST).
   *
   * @param cucumblan the cucumblan
   * @return the report
   */
  /* Main method to do the actual Maven tests. */
  public Report doInitialTest(Cucumblan cucumblan) {

    logger.debug(
        "Store reports in folder: " + this.addSlash(this.storagePath) + cucumblan.getFolder());
    logger.debug("Cucumblan: " + cucumblan.toString());
    cucumblan.setSessionNr(1); //First session for this specific test (POST).
    
    /* Initialize response. */
    Report response = new Report();
    response.setError(FileManagement.NOERROR);
    response.setCreationTime(FileManagement.whatTime());
    response.setSkipResponseValidation(cucumblan.getSkipResponseValidation());
    response.setSessionNr(cucumblan.getSessionNr());
    final String testId = cucumblan.getUuid().toString();
    response.setTestId(cucumblan.getUuid());

    /* Paths for the results in the filesystem and URLs. */
    final String testIdFolder =
        this.storagePath + File.separator + cucumblan.getFolder() + File.separator;
    final String reportFolder =
        this.storagePath + File.separator + cucumblan.getFolder() + File.separator
            + cucumblan.getSessionNr();

    final String skip = ".*=IGNORE";
    final String skipProp = testIdFolder + File.separator + "exclude-response.properties";
    final String reportURL = this.serverHost + File.separator + cucumblan.getFolder()
        + File.separator + cucumblan.getSessionNr();
    final String propsUrl = this.serverHost + File.separator + cucumblan.getFolder();
    final String allSessions = testIdFolder + File.separator + ALLTESTS;

    try {


      /* Take care of the folder where all the files and reports will be stored. */
      if (!new File(this.storagePath).exists()) {
        new File(this.storagePath).mkdir();
      }
      if (!new File(testIdFolder).exists()) {
        new File(testIdFolder).mkdir();
      }
      if (!new File(reportFolder).exists()) {
        new File(reportFolder).mkdir();
      }
      if (!new File(reportFolder).exists()) {
        logger.error("Cannot create folder " + reportFolder);
        response.setError("Cannot create folder " + reportFolder);
        return response;
      }

      /* Generate the cucumblan.properties and some other files.*/
      boolean isSaved = FileManagement.saveCucumblan(testIdFolder, cucumblan.toString());
      String postmanFile = testIdFolder + File.separator + cucumblan.getUploadFilename();
      FileManagement.writeFilestream(postmanFile, cucumblan.getInputStream());
      logger.info("skipResponseValidation: " + cucumblan.getSkipResponseValidation());
      if (cucumblan.getSkipResponseValidation()) {
        FileManagement.writeString(skipProp, skip);
        logger.info("Responses will not be validated.");
      }

      /* Execute mvn test. */
      if (cucumblan.getExecute()) {
        long startTime = System.nanoTime();
        response.setStartTime(FileManagement.whatTime());

        /* Here comes the actual man Maven test execution.
         **/
        String result = mvnTest(testIdFolder, cucumblan.getSessionNr());

        /* Calcuate execution time. */
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        Duration d = Duration.ofNanos(duration);
        response.setDurationSeconds(Long.valueOf(d.getSeconds()));
        response.setEndTime(FileManagement.whatTime());

        /* Check test result. */
        boolean isSuccess = Boolean.valueOf(result);
        response.setSuccess(isSuccess);
        if (isSuccess == false && result.equalsIgnoreCase("false") == false) {
          response.setError(result);
          response.setTestExecuted(false);
          response.setMessage("An internal error occured.");
          return response;
        } else {
          response.setTestExecuted(true);
        }

      } else {
        /* Message */
        response.setLinkToFeature("Not generated");
        response.setLinkToReport("Not generated.");
        response.setTestExecuted(false);
        response.setMessage("Property file updated, no test executed (execute=false).");
        if (isSaved == false) {
          response.setError("Error: Could not save file.");
        }
        return response;
      }

      /* Copy generated feature file to the public folder. */
      String target = reportURL + File.separator + FEATUREX + "0" + DOTFEATURE;
      response.setLinkToFeature(target);

      /* Copy the cucumber report folder and send back the link
       * to the main report html file: report-feature_1959214294.html.
       * */
      String linkReport = reportURL + File.separator + REPORTOVERVIEW;
      logger.info("Generated report html file: " + linkReport);
      response.setLinkToReport(linkReport);
      response.setLinkToProperties(propsUrl + File.separator + FileManagement.PROPERTIES);
      response.setLinkToSessions(propsUrl + File.separator + ALLTESTS);

      /* Message */
      response.setMessage("Report created.");
      response.setError(FileManagement.NOERROR);

      /* Persist test execution in JSON file. */
      List<Report> list = new ArrayList<Report>();
      list.add(response);

      PersistJSON.writeJSON(testIdFolder + File.separator + LASTTEST, response);
      String array = PersistJSON.writeArray(list, allSessions);
      FileManagement.writeString(allSessions, array);


    } catch (Exception e) {
      if (response == null) {
        response = new Report();
      }
      response.setError(e.getLocalizedMessage());
      response.setMessage("No reports.");
      response.setSuccess(false);

    }

    return response;
  }

  /**
   * The actual main Maven test execution.
   *
   * @param reportFolder
   * @return the result as String. "true" if success, "false" if test failed, or Exception message
   * if an error occured.
   */
  private String mvnTest(String reportFolder, int runId) {
    logger.debug("Starting Maven test in folder " + reportFolder);
    int status = 0;
    String result = null;
    boolean isSuccess = false;
    try {
      //addToClasspath(reportFolder);
      status = IdaithalamExecutor
          .validateContract("IDAI server test execution", reportFolder, runId);
      logger.info("Execution status: " + status);
      if (status != 0) {
        isSuccess = false;
      } else {
        isSuccess = true;
      }
      result = Boolean.valueOf(isSuccess).toString();
    } catch (Exception e) {
      result = e.getLocalizedMessage();
      logger.info("Maven execution Execption raised: " + result);
    }
    return result;
  }

  private String addSlash(String url) {
    if (url.endsWith("/")) {
      return url;
    } else {
      return url + "/";
    }
  }


  /**
   * List all i ds testidlist.
   *
   * @return the testidlist
   */
  public Testidlist listAllIDs() {
    List<String> list = FileManagement.listFolders(this.storagePath);
    Testidlist idlist = new Testidlist();
    for (String folder : list) {
      idlist.addServerUrlItem(this.serverHost + "/" + folder + "/" + ALLTESTS);
      idlist.addIdListItem(folder);
    }
    if (list.size() == 0) {
      idlist.addIdListItem("empty");
      idlist.addServerUrlItem("empty");
    }

    return idlist;
  }

  /**
   * Remove.
   *
   * @param testId the test id
   */
  public void remove(String userId, java.lang.String testId) {
    //TODO check user
    final String reportFolder = this.storagePath + File.separator + testId;
    FileManagement.removeFolder(reportFolder);
  }


}
