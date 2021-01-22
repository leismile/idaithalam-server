package ch.inss.idaiserver.model;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;


@Table(name="test")
@Entity
public class TestDao   {
    
  public TestDao(Test test){
      this.uuid = test.getTestid();
      this.creationtime = test.getCreationtime();
      this.lastexecution = test.getLastexecution();
      this.load = test.getLoad();
      //List<Serviceapi> api = test.getServiceapi();
      		  
     // this.serviceapi = test.getServiceapi();
  }
  
  public TestDao() {
	  
  }
    
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int testdbid;
    
  private UUID uuid;

  private String creationtime;

  private String lastexecution;

  @ElementCollection
  private List<String> load = null;
  
  private HashMap<String, String> serviceapi = null;

//  @OneToMany(targetEntity=ServiceapiDao.class, mappedBy="serviceapidbid", fetch=FetchType.EAGER)
//  private List<ServiceapiDao> serviceapi = null;

  private String type;

  private String message;

  private String error;
  
  

  public int getTestdbid() {
	return testdbid;
}

public void setTestdbid(int testdbid) {
	this.testdbid = testdbid;
}

public UUID getUuid() {
	return uuid;
}

public void setUuid(UUID uuid) {
	this.uuid = uuid;
}

public String getCreationtime() {
	return creationtime;
}

public void setCreationtime(String creationtime) {
	this.creationtime = creationtime;
}

public String getLastexecution() {
	return lastexecution;
}

public void setLastexecution(String lastexecution) {
	this.lastexecution = lastexecution;
}

public List<String> getLoad() {
	return load;
}

public void setLoad(List<String> load) {
	this.load = load;
}

public HashMap<String, String> getServiceapi() {
	return serviceapi;
}

public void setServiceapi(HashMap<String, String> serviceapi) {
	this.serviceapi = serviceapi;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public String getError() {
	return error;
}

public void setError(String error) {
	this.error = error;
}

@Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TestDao {\n");
    
    sb.append("    testid: ").append(toIndentedString(uuid)).append("\n");
    sb.append("    creationtime: ").append(toIndentedString(creationtime)).append("\n");
    sb.append("    lastexecution: ").append(toIndentedString(lastexecution)).append("\n");
    sb.append("    load: ").append(toIndentedString(load)).append("\n");
//    sb.append("    serviceapi: ").append(toIndentedString(serviceapi)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
    sb.append("}");
    return sb.toString();
  }
  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}

