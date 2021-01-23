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
  


  public Report doTests()      throws Exception {
	  
	logger.debug("Store reports in folder: " + this.storagePath);
	logger.debug("Cucumblan: " + cucumblan.toString());
	
    final String FEATURE = "virtualan-contract.feature";
    final String REPORTS = "target/cucumber-html-reports/";
    
    /* Initialize response. */
    Report links = new Report();
    links.setError(FileManagement.NOERROR);
    links.setCreationtime(FileManagement.whatTime());
    
    final String testId = cucumblan.getUuid().toString();
    links.setTestid(cucumblan.getUuid());
    
    
    if (!new File("conf").exists()) {
      new File("conf").mkdir();
    }
    if (!new File("conf/" + testId).exists()) {
      new File("conf/" + testId).mkdir();
    }
    /* Generate the cucumblan.properties file.*/
    boolean isSaved = false;
    if ( cucumblan.getOverwrite()) {
    	FileManagement.save("conf/" + testId, cucumblan.toString());	
    }
    
    
    
    
    /* Execute mvn test. */
    if (cucumblan.getExecute()) {
      String reportfolder = this.storagePath + FileManagement.fs + cucumblan.getUuid().toString();
      links.executiontime(FileManagement.whatTime());
      boolean isSuccess = mvnTest(reportfolder);
      links.setSuccess(isSuccess);
      removeFromClasspath(reportfolder);
    } else {
      /* Message */
      links.setLinktofeature("Not generated");
      links.setLinktoreport("Not generated.");
      links.setMessage("Property file updated, no test executed (execute=false).");
      links.setError(FileManagement.NOERROR);
      return links;
    }

    /* Copy generated feature file to the public folder. */
    String target = "http://35.193.57.60/"+testId + "/feature/virtualan-contract.0.feature";
    links.setLinktofeature(target);

    /* Copy the cucumber report folder and send back the link
     * to the main report html file: report-feature_1959214294.html.
     * */
    String linkReport = "http://35.193.57.60/"+testId + "/cucumber-html-reports/overview-features.html";
    logger.info("Generated report html file: " + linkReport);
    links.setLinktoreport(linkReport);

    /* Message */
    links.setMessage("Report created.");
    links.setError(FileManagement.NOERROR);
    return links;
  }

	private boolean mvnTest(String reportfolder)
			throws MalformedURLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//		addToClasspath("conf/" + testId);
		addToClasspath(reportfolder);
	      int status = 0;
	      boolean isSuccess = false;
	      try {
	        status = IdaithalamExecutor
	            .validateContract("IDAI Server test Execution", reportfolder);
	        logger.info("Execution status: " + status);
	        if (status != 0) {
	            isSuccess = false;
	        } else {
	            isSuccess = true;
	        }
	      } catch (Exception e) {
	        logger.info("Execution execption raised: " + e.getMessage());
	        isSuccess = false;
	      }
		return isSuccess;
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
  
  
}
