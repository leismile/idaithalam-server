package ch.inss.idaiserver.service;

import ch.inss.idaiserver.exception.UnableProcessException;
import ch.inss.idaiserver.model.Conf;
import ch.inss.idaiserver.utils.FileManagement;
import java.io.File;
import java.io.FileReader;
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
      String fileContent = update(testId, conf);
      if (fileContent == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } else {
        return new ResponseEntity<>(fileContent, HttpStatus.OK);
      }
    } catch (UnableProcessException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * 	"serverUrl" : ["pets=http://localhost:8800", "order=http://localhost:8900", "http://localhost:8600"]
   * Update to the cucumblan.properties file.
   */
  public String update(String testId, Conf conf) throws UnableProcessException {
    String content = null;
    String key = "service.api";
    Properties prop;
    final String folder =
        this.storagePath + FileManagement.fs + testId;
    File file = new File(folder);
    if (file.exists()) {
      prop = FileManagement.readCucumblanPropertiesFile(folder);
      List<String> urls = conf.getServerUrl();
      if (prop != null && urls != null) {
        urls.stream().forEach(x ->
        {
          String[] values = x.split("=");
          if (values.length == 2) {
            prop.put(key + "." + values[0], values[1]);
          } else {
            prop.put(key, values[0]);
          }
        });
        FileManagement.saveCucumblan(folder, getPropertyAsString(prop));
        return getPropertyAsString(prop);
      }
    }
    logger.error("Could not add line: " + conf.toString());
    throw new UnableProcessException("Could not add line: " + conf.toString());
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
    final String folder =
        this.storagePath + FileManagement.fs + testId + FileManagement.fs + fileName;
    File file = new File(folder);
    try {
      if (file.exists()) {
        fileContent = IOUtils.toString(new FileReader(file));
      } else {
        logger.error("Object for cucumblan service not correctly initiazlized.");
      }
    } catch (Exception e) {
      logger.error("Unable to read the fle.");
    }
    return fileContent;
  }
}
