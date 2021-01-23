package ch.inss.idaiserver.service;

import ch.inss.idaiserver.service.Cucumblan;
import ch.inss.idaiserver.model.Report;
import ch.inss.idaiserver.utils.FileManagement;
import io.virtualan.idaithalam.contract.IdaithalamExecutor;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Stack;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class TestService {

  private static final Logger logger = LoggerFactory.getLogger(TestService.class);
  
  /** Folder to store the Cucumber reports. */
  @Value("${ch.inss.idaiserver.reports.folder}")
  private String storagePath;
  
  @Value("${ch.inss.idaiserver.host}")
  private String serverHost;
  
  @Autowired private Cucumblan cucumblan;
  
  private static final String FEATURE = "virtualan-contract.feature";
  private static final String REPORTS = "cucumber-html-reports/";
  
  

  /* Main method to do the actual Maven tests. */
  public Report doTest()      {
	  
	logger.debug("Store reports in folder: " + this.addSlash(this.storagePath) + cucumblan.getFolder());
	logger.debug("Cucumblan: " + cucumblan.toString());
	Report links = new Report();
	try {
		
	    /* Initialize response. */
	    links.setError(FileManagement.NOERROR);
	    links.setCreationtime(FileManagement.whatTime());
	    links.setSkipResponseValidation(cucumblan.getSkipResponseValidation());
	    
	    final String testId = cucumblan.getUuid().toString();
	    links.setTestid(cucumblan.getUuid());
	    
	    /* Folder for the results to store into the filesystem. */
		final String reportFolder = this.storagePath + FileManagement.fs + cucumblan.getFolder();
		final String confFolder = reportFolder + FileManagement.fs + "conf" + FileManagement.fs;
		final String skip = ".*=IGNORE";
		final String skipProp = confFolder + "exclude-response.properties";
		
		    
	    /* Take care of the folder where all the files and reports will be stored. */
	    if (!new File(reportFolder).exists()) {
	      new File(reportFolder).mkdir();
	      new File(confFolder).mkdir();
	    }
	    if (!new File(confFolder).exists()) {
	      logger.error("Cannot create folder " + confFolder);
	      links.setError("Cannot create folder " + confFolder);
	      return links;
	    }
	    /* Generate the cucumblan.properties and some other files.*/
	    boolean isSaved =  FileManagement.saveCucumblan(confFolder, cucumblan.toString());
	    String postmanFile = confFolder + cucumblan.getUploadFilename();
    	FileManagement.writeFilestream(postmanFile, cucumblan.getInputStream());
    	logger.info("skipResponseValidation: " + cucumblan.getSkipResponseValidation());
    	if ( cucumblan.getSkipResponseValidation()) {
    		FileManagement.writeString(skipProp, skip);
    		logger.info("Responses will not be validated.");
    	}
	    
	    /* Execute mvn test. */
	    if (cucumblan.getExecute()) {
	      
	      links.executiontime(FileManagement.whatTime());
	      String result =  mvnTest(reportFolder);
	      boolean isSuccess = Boolean.valueOf(result);
	      links.setSuccess(isSuccess);
	      if ( isSuccess == false && result.equalsIgnoreCase("false") == false) {
	    	  links.setError(result);
	    	  links.testexecuted(false);
	    	  links.setMessage("An internal error occured.");
	    	  return links;
	      }else {
	    	  links.testexecuted(true);
	      }
	      
	      
	    } else {
	      /* Message */
	      links.setLinktofeature("Not generated");
	      links.setLinktoreport("Not generated.");
	      links.testexecuted(false);
	      links.setMessage("Property file updated, no test executed (execute=false).");
	      if ( isSaved == false ) {
	    	  links.setError("Error: Could not save file.");
	      }
	      return links;
	    }
	
	    /* Copy generated feature file to the public folder. */
	//    String target = "http://35.193.57.60/"+testId + "/feature/virtualan-contract.0.feature";
	    String target = serverHost + "/" + cucumblan.getFolder() + "/feature/virtualan-contract.0.feature";
	    
	    links.setLinktofeature(target);
	
	    /* Copy the cucumber report folder and send back the link
	     * to the main report html file: report-feature_1959214294.html.
	     * */
	//    String linkReport = "http://35.193.57.60/"+testId + "/cucumber-html-reports/overview-features.html";
	    String linkReport = serverHost + "/" + cucumblan.getFolder() + "/cucumber-html-reports/overview-features.html";
	    logger.info("Generated report html file: " + linkReport);
	    links.setLinktoreport(linkReport);
	    
	    /* Message */
	    links.setMessage("Report created.");
	    links.setError(FileManagement.NOERROR);
    
	}catch(Exception e) {
		links.setError(e.getLocalizedMessage());
		links.setMessage("No reports.");
		links.setSuccess(false);
		
	}

    return links;
  }

	private String mvnTest(String reportFolder){
		int status = 0;
		String result = null;
		boolean isSuccess = false;
		try {
			addToClasspath(reportFolder);
			status = IdaithalamExecutor.validateContract("IDAI server test execution", reportFolder);
			logger.info("Execution status: " + status);
			if (status != 0) {
				isSuccess = false;
			} else {
				isSuccess = true;
			}
			removeFromClasspath(reportFolder);
			result = Boolean.valueOf(isSuccess).toString();
		} catch (MalformedURLException e) {
			result = e.getLocalizedMessage();
			logger.info("Maven execution MalformedURLException raised: " + result);
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			result = e.getLocalizedMessage();
			logger.info("Maven execution NoSuchMethodException raised: " + result);
			e.printStackTrace(); 
		} catch (InvocationTargetException e) {
			result = e.getLocalizedMessage();
			logger.info("Maven execution InvocationTargetException raised: " + result);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			result = e.getLocalizedMessage();
			logger.info("Maven execution IllegalAccessException raised: " + result);
			e.printStackTrace();
		} catch (Exception e) {
			result = e.getLocalizedMessage();
			logger.info("Maven execution Execption raised: " + result);
			e.printStackTrace();
		}
//	      catch (Exception e) {
//	        logger.info("Execution execption raised: " + e.getLocalizedMessage());
//	      }
      finally {
    	  
      }
	return result;
	}

  private void addToClasspath(String s)
      throws MalformedURLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    File f = new File(s);
    URI u = f.toURI();
    URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
    Class<URLClassLoader> urlClass = URLClassLoader.class;
    Method method = urlClass.getDeclaredMethod("addURL", new Class[]{URL.class});
    method.setAccessible(true);
    method.invoke(urlClassLoader, new Object[]{u.toURL()});
  }
  

  private static void removeFromClasspath(String path) throws Exception {
    URL url = new File(path).toURI().toURL();
    URLClassLoader urlClassLoader = (URLClassLoader)
        ClassLoader.getSystemClassLoader();
    Class<?> urlClass = URLClassLoader.class;
    Field ucpField = urlClass.getDeclaredField("ucp");
    ucpField.setAccessible(true);
//    URLClassPath ucp = (URLClassPath) ucpField.get(urlClassLoader);
//    Class<?> ucpClass = URLClassPath.class;
//    Field urlsField = ucpClass.getDeclaredField("urls");
//    urlsField.setAccessible(true);
//    Stack urls = (Stack) urlsField.get(ucp);
//    urls.remove(url);

//    urlsField = ucpClass.getDeclaredField("path");
//    urlsField.setAccessible(true);
//    List urlss = (List) urlsField.get(ucp);
//    urlss.remove(url);
  }
  
  private String addSlash(String url) {
	  if (url.endsWith("/")) {
		  return url;
	  }else {
		  return url + "/";
	  }
  }
  
  
}
