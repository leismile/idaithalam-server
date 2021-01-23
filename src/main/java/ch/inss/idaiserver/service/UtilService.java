package ch.inss.idaiserver.service;

import ch.inss.idaiserver.utils.FileManagement;
import java.io.File;
import java.io.FileReader;
import java.util.UUID;
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


  public ResponseEntity<String> getContent(String testId, String fileName) {
    String fileContent = readProps(UUID.fromString(testId), fileName);
    if(fileContent == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }else {
      return new ResponseEntity<>(fileContent, HttpStatus.OK);
    }
  }


  /**
   *
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
      if(file.exists()){
        fileContent = IOUtils.toString(new FileReader(file));
      } else {
        logger.error("Object for cucumblan service not correctly initiazlized.");
      }
    }catch (Exception e){
      logger.error("Unable to read the fle.");
    }
    return fileContent;
  }
}
