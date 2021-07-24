package ch.inss.virtualan.idaiserver.service;

import ch.inss.virtualan.idaiserver.model.Report;
import ch.inss.virtualan.idaiserver.utils.FileManagement;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * The type Cucumblan.
 */
@Component
public class Cucumblan {

  private static final String dataload = "virtualan.data.load";
  private static final String datatype = "virtualan.data.type";
  private static final String serviceapi = "service.api";
  private static final Logger logger = LoggerFactory.getLogger(Cucumblan.class);
  private String userId = "demo";
  private String workspace = "demo";
  private String staging = "demo";
  private String version = "v1";
  private boolean isDemo = false;
  
  private String TYPE;
  private InputStream inputStream;
  private String folder;
  private String uploadFilename;
  private Integer sessionNr;
  private Boolean execute;
  private Boolean skipResponseValidation;
  private UUID uuid;
  /**
   * Store keys as key=service.api. Add resourcees with key=service.api.nextresource.
   */
  private HashMap<String, String> URL;
  /* List of all postman collection files. */
  private LinkedHashSet<String> postmanCollections;  //TODO: check if always only one.

  /**
   * Instantiates a new Cucumblan object for demo requests.
   */
  public Cucumblan() {

  }

  /** Constructor for logged in user. */
  public Cucumblan(String userId, String workspace, String staging, String version, boolean isDemo) {
    this.userId = userId;
    this.workspace = workspace;
    this.staging = staging;
    this.version = version;
    this.isDemo = isDemo;
  }

  /**
   * Init.
   */
  public void init() {
    this.uuid = UUID.randomUUID();
    this.iniAll();
  }

  /**
   * Init.
   *
   * @param uuid the uuid
   */
  public void init(UUID uuid) {
    this.uuid = uuid;
    this.iniAll();
  }

  /**
   * uuid must be set already.
   */
  private void iniAll() {
    if (this.uuid == null) {
      logger.error("Wrong initialization. UUID must be set.");
      return;
    }
    this.execute = new Boolean(true);
//    this.folder = this.uuid.toString();
    this.postmanCollections = new LinkedHashSet<String>();
    this.URL = new HashMap<String, String>();
    this.sessionNr = new Integer(0);
    this.TYPE = "POSTMAN";
  }

  public boolean isDemo() {
    return isDemo;
  }

  public void setDemo(boolean demo) {
    isDemo = demo;
  }

  public String getWorkspace() {
    return workspace;
  }

  public void setWorkspace(String workspace) {
    this.workspace = workspace;
  }

  public String getStaging() {
    return staging;
  }

  public void setStaging(String staging) {
    this.staging = staging;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  /**
   * Gets folder.
   *
   * @return the folder
   */
  public String getFolder() {
    return folder;
  }

  /**
   * Gets upload filename.
   *
   * @return the upload filename
   */
  public String getUploadFilename() {
    return uploadFilename;
  }

  /**
   * Sets upload filename.
   *
   * @param uploadFilename the upload filename
   */
  public void setUploadFilename(String uploadFilename) {
    this.uploadFilename = uploadFilename;
  }

  /**
   * Gets uuid.
   *
   * @return the uuid
   */
  public UUID getUuid() {
    return uuid;
  }

  /**
   * Gets session nr.
   *
   * @return the session nr
   */
  public Integer getSessionNr() {
    return sessionNr;
  }


  /**
   * Sets session nr.
   *
   * @param sessionNr the session nr
   */
  public void setSessionNr(Integer sessionNr) {
    this.sessionNr = sessionNr;
  }


  /**
   * Gets input stream.
   *
   * @return the input stream
   */
  public InputStream getInputStream() {
    return inputStream;
  }

  /**
   * Sets input stream.
   *
   * @param inputStream the input stream
   */
  public void setInputStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   * Gets type.
   *
   * @return the type
   */
  public String getTYPE() {
    return TYPE;
  }

  /**
   * Sets type.
   *
   * @param tYPE the t ype
   */
  public void setTYPE(String tYPE) {
    TYPE = tYPE;
  }

  /**
   * Gets execute.
   *
   * @return the execute
   */
  public Boolean getExecute() {
    return execute;
  }

  /**
   * Sets execute.
   *
   * @param execute the execute
   */
  public void setExecute(Boolean execute) {
    this.execute = execute;
  }


  /**
   * Gets skip response validation.
   *
   * @return the skip response validation
   */
  public Boolean getSkipResponseValidation() {
    return skipResponseValidation;
  }


  /**
   * Sets skip response validation.
   *
   * @param skipResponseValidation the skip response validation
   */
  public void setSkipResponseValidation(Boolean skipResponseValidation) {
    this.skipResponseValidation = skipResponseValidation;
  }


  /**
   * Input is only the resource. service.api is added.
   *
   * @param resource the resource
   * @param value    the value
   */
  public void addURL(String resource, String value) {
    if (resource == null || "".equals(resource)) {
      resource = "service.api";
    } else {
      resource = "service.api." + resource;
    }
    this.URL.put(resource, value);

  }

  /**
   * Add a postman collection file name for the property virtualan.data.load.
   *
   * @param dataload the dataload
   */
  public void addFILE(String dataload) {
    if (dataload == null) {
      return;
    }
    this.postmanCollections.add(dataload);
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer("#Generated " + FileManagement.whatTime());
    sb.append(FileManagement.lf);
    for (String key : this.URL.keySet()) {
      sb.append(key).append(" = ").append(this.URL.get(key)).append(FileManagement.lf);
    }
    sb.append(dataload).append(" = ");
    for (String filename : this.postmanCollections) {
      sb.append(filename).append(";");
    }
    sb.append(FileManagement.lf);
    sb.append(datatype).append(" = ").append(this.TYPE).append(FileManagement.lf);

    return sb.toString();
  }

  /**
   * From property.
   *
   * @param path the path
   * virtualan.data.type=POSTMAN
   */
  public void fromProperty(String path) {

    Properties p = FileManagement.readCucumblanPropertiesFile(path);

    /* POSTMAN, VIRTUALAN or EXCEL. */
    this.TYPE = p.getProperty(datatype);

    /* Semicolon separated list of postman collections. */
    String post = p.getProperty(dataload);
    String[] posts = post.split(";");
    this.postmanCollections = new LinkedHashSet(Arrays.asList(posts));

    /* List of URLs with resources */
    for (Object key : p.keySet()) {
      if (key.toString().startsWith(serviceapi)) {
        this.URL.put(key.toString(), p.get(key).toString());
      }
    }

  }

  /**
   * Get a single file.
   *
   * @return the one file
   */
  public String getOneFILE() {
    if (this.postmanCollections.size() > 1) {
      logger.warn(
          "There are more files in this configuration. This method should be only called for a single file configuration like instan execution for a test. ");
    }
    if (this.postmanCollections == null || this.postmanCollections.isEmpty()) {
      logger.error("There was no postman collection file added.");
      return null;
    }
    Iterator<String> iterator = this.postmanCollections.iterator();
    if (iterator.hasNext()) {
      return iterator.next();
    } else {
      return "";
    }
  }

  /**
   * Report factory report.
   *
   * @return the report
   */
  public Report reportFactory() {
    Report reportLinks = new Report();
    reportLinks.setError(FileManagement.NOERROR);
    reportLinks.setCreationTime(FileManagement.whatTime());
    reportLinks.setTestId(this.uuid);
    reportLinks.setSessionNr(this.sessionNr);
    return reportLinks;
  }


  /**
   * Gets url.
   *
   * @return the url
   */
  public HashMap<String, String> getURL() {
    return URL;
  }

  /**
   * Gets file.
   *
   * @return the file
   */
  public LinkedHashSet<String> getFILE() {
    return postmanCollections;
  }

//TODO Question if UUID adding to ID ?
  /** Genereate test ID from userId, workspace, staging and version.
   * */
  public String generateIdAndFolder() {
    StringBuilder id = null;
    id.append(this.userId).append(".").append(this.workspace).append(".").append(this.staging).append(".").append(this.version);
    if ( this.isDemo == false ){
      this.folder = id.toString();
    }else{
      this.folder = this.uuid.toString();
    }
    return id.toString();
  }
}
