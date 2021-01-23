package ch.inss.idaiserver.service;

import ch.inss.idaiserver.model.Cucumblan;
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
import org.springframework.stereotype.Service;


@Service
public class TestService {

  private static final Logger logger = LoggerFactory.getLogger(TestService.class);

  public static void removeFromClasspath(String path) throws Exception {
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

  public Report doTests(Cucumblan cucumblan)
      throws Exception {
    final String FEATURE = "virtualan-contract.feature";
    final String REPORTS = "target/cucumber-html-reports/";
    Report links = new Report();
    links.setError(FileManagement.NOERROR);
    links.setCreationtime(FileManagement.whatTime());
    UUID testId = cucumblan.getUuid();
    links.setTestid(testId);
    if (!new File("conf").exists()) {
      new File("conf").mkdir();
    }
    if (!new File("conf/" + testId.toString()).exists()) {
      new File("conf/" + testId.toString()).mkdir();
    }
    /* Generate the cucumblan.properties file.*/
    boolean isSaved = FileManagement.save("conf/" + testId.toString(), cucumblan);
    /* Execute mvn test. */
    if (cucumblan.getExecute()) {
      links.executiontime(FileManagement.whatTime());
      addToClasspath("conf/" + testId.toString());
      int status = 0;
      boolean isSuccess = false;
      try {
        status = IdaithalamExecutor
            .validateContract("IDAI Server test Execution", "conf/" + testId.toString());
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
      links.setSuccess(isSuccess);
      removeFromClasspath("conf/" + testId.toString());
    } else {
      /* Message */
      links.setLinktofeature("Not generated");
      links.setLinktoreport("Not generated.");
      links.setMessage("Property file updated, no test executed (execute=false).");
      links.setError(FileManagement.NOERROR);
      return links;
    }

    /* Copy generated feature file to the public folder. */
    String target = "http://report.idaithalam.io/"+testId.toString() + "/feature/virtualan-contract.0.feature";
    links.setLinktofeature(target);

    /* Copy the cucumber report folder and send back the link
     * to the main report html file: report-feature_1959214294.html.
     * */
    String linkReport = "http://report.idaithalam.io/"+testId.toString() + "/cucumber-html-reports/overview-features.html";
    logger.info("Generated report html file: " + linkReport);
    links.setLinktoreport(linkReport);

    /* Message */
    links.setMessage("Report created.");
    links.setError(FileManagement.NOERROR);
    return links;
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
}
