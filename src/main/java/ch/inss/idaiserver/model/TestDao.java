package ch.inss.idaiserver.model;

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
//      this.serviceapi = test.getServiceapi();
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
  
  @ElementCollection
  private List<String> serviceapi = null;

//  @OneToMany(targetEntity=ServiceapiDao.class, mappedBy="serviceapidbid", fetch=FetchType.EAGER)
//  private List<ServiceapiDao> serviceapi = null;

  private String type;

  private String message;

  private String error;

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

