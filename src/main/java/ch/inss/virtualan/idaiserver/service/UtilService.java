package ch.inss.virtualan.idaiserver.service;

import ch.inss.virtualan.idaiserver.exception.UnableProcessException;
import ch.inss.virtualan.idaiserver.model.Conf;
import ch.inss.virtualan.idaiserver.model.Report;
import ch.inss.virtualan.idaiserver.utils.FileManagement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UtilService {

  private static final Logger logger = LoggerFactory.getLogger(UtilService.class);

  @Value("${ch.inss.idaiserver.reports.folder}")
  private String storagePath;
  @Value("${ch.inss.idaiserver.host}")
  private String serverHost;


  private static String getPropertyAsString(Properties prop) {
    return prop.entrySet()
        .stream()
        .map(e -> e.getKey() + "=" + e.getValue())
        .collect(Collectors.joining("\n"));
  }

  public ResponseEntity<String> updateCucumblan(String testId, Conf conf) {
    try {
      String fileContent = updateProps(testId, conf);
      if (fileContent == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } else {
        return new ResponseEntity<>(fileContent, HttpStatus.OK);
      }
    } catch (UnableProcessException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  public ResponseEntity<String> deleteCucumblanPropKey(String testId, String key) {
    try {
      String fileContent = deleteProps(testId, key);
      if (fileContent == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } else {
        return new ResponseEntity<>(fileContent, HttpStatus.OK);
      }
    } catch (UnableProcessException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  private String deleteProps(String testId, String key) throws UnableProcessException {
    Properties prop;
    if (isCucumblanExists(testId)) {
      final String folder =
          this.storagePath + FileManagement.fs + testId;
      prop = FileManagement.readCucumblanPropertiesFile(folder);
      if (prop != null && key != null) {
        prop.remove(key);
      }
      FileManagement.saveCucumblan(folder, getPropertyAsString(prop));
      return getPropertyAsString(prop);
    }
    logger.error("Could not remove line: " + key);
    throw new UnableProcessException("Could not remove line: " + key);
  }


  private boolean isCucumblanExists(String testId) {
    return new File(this.storagePath + FileManagement.fs + testId + FileManagement.fs
        + FileManagement.PROPERTIES).exists();
  }

  /**
   * "serverUrl" : ["pets=http://localhost:8800", "order=http://localhost:8900",
   * "http://localhost:8600"] Update to the cucumblan.properties file.
   */
  private String updateProps(String testId, Conf conf) throws UnableProcessException {
    String key = "service.api";
    Properties prop;
    if (isCucumblanExists(testId)) {
      final String folder =
          this.storagePath + FileManagement.fs + testId;
      prop = FileManagement.readCucumblanPropertiesFile(folder);
      List<String> urls = conf.getServerUrls();
      if (prop != null && urls != null) {
        addServerUrls(key, prop, urls);
      }
      FileManagement.saveCucumblan(folder, getPropertyAsString(prop));
      return getPropertyAsString(prop);
    }
    logger.error("Could not add line: " + conf.toString());
    throw new UnableProcessException("Could not add line: " + conf.toString());
  }

  private void addServerUrls(String key, Properties prop, List<String> urls) {
    urls.forEach(x ->
    {
      String[] values = x.split("=");
      if (values.length == 2) {
        prop.put(key + "." + values[0], values[1]);
      } else {
        prop.put(key, values[0]);
      }
    });
  }


  /** Get the feature file from very last session. 
 * @param testId
 * @return
 */
public ResponseEntity<String> getSessionContent(String testId, String file) {
//    final String FEATURE = "feature"+FileManagement.fs+"virtualan-contract.0.feature";
    Report report;
	try {
		/* Get last session number. */
		report = this.getLastSession(testId);
	    String nr = null;
	    if (report == null || report.getSessionNr() == null) nr = "1";
	    else nr = report.getSessionNr().toString();
	    
	    /* Get feature file content.*/
	    String fileName = "/" + nr + "/" + file;
	    String fileContent = readProps(UUID.fromString(testId), fileName);
	    if (fileContent == null) {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    } else {
	      return new ResponseEntity<>(fileContent, HttpStatus.OK);
	    }
	} catch (IOException e) {
		logger.error(e.getLocalizedMessage());
		e.printStackTrace();
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
  }
public ResponseEntity<String> getContent(String testId, String fileName) {
    String fileContent = readProps(UUID.fromString(testId), fileName);
    if (fileContent == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(fileContent, HttpStatus.OK);
    }
  }


  /**
   * @param testId
   * @return
   */
  private String readProps(UUID testId, String fileName) {
    String fileContent = null;
    /* Paths for the results in the filesystem and URLs. */
    try {
      if (isCucumblanExists(testId.toString())) {
        File file = new File(
            this.storagePath + FileManagement.fs + testId + FileManagement.fs + fileName);
        fileContent = IOUtils.toString(new FileReader(file));
      } else {
        logger.error("Object for cucumblan service not correctly initiazlized.");
      }
    } catch (Exception e) {
      logger.error("Unable to read the fle.");
    }
    return fileContent;
  }

  public ResponseEntity<Report> readLatestTestResult(String testId) {
    final String folder =
        this.storagePath + FileManagement.fs + testId + FileManagement.fs + TestService.LASTTEST;
    File file = new File(folder);
    if (file.exists()) {
      try {
        Report report = PersistJSON.readLatestReport(folder);
        if (report != null) {
          return new ResponseEntity<>(report, HttpStatus.OK);
        }
      } catch (Exception e) {
        logger.warn("Unable to load latest json response");
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }


  public ResponseEntity<List<Report>> readAllReport(String testId) {
    final String folder =
        this.storagePath + FileManagement.fs + testId + FileManagement.fs + TestService.ALLTESTS;
    File file = new File(folder);
    if (file.exists()) {
      try {
        List<Report> report = PersistJSON.readReports(folder);
        if (report != null) {
          return new ResponseEntity<>(report, HttpStatus.OK);
        }
      } catch (Exception e) {
        logger.warn("Unable to load latest json response");
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  public Report getLastSession(String testId) throws FileNotFoundException, IOException {
		final String LASTTEST = "lasttest.json";
		final String reportFolder = storagePath + File.separator + testId;
		final String lastSession = reportFolder + File.separator + LASTTEST;
		String json = FileManagement.readToString(lastSession);
	    Report links = PersistJSON.reportFromJSON(json);
		return links;
		
	}
  
  
}