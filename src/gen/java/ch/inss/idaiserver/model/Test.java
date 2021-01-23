package ch.inss.idaiserver.model;

import java.util.Objects;
import ch.inss.idaiserver.model.Serviceapi;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * virtualan.data.load&#x3D;sample.postman_collection.json  virtualan.data.type&#x3D;POSTMAN service.api.next&#x3D;http://localhost:8081
 */
@ApiModel(description = "virtualan.data.load=sample.postman_collection.json  virtualan.data.type=POSTMAN service.api.next=http://localhost:8081")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-01-23T06:06:06.086944+01:00[Europe/Zurich]")
public class Test   {
  @JsonProperty("testid")
  private UUID testid;

  @JsonProperty("creationtime")
  private String creationtime;

  @JsonProperty("lastexecution")
  private String lastexecution;

  @JsonProperty("load")
  @Valid
  private List<String> load = null;

  @JsonProperty("serviceapi")
  @Valid
  private List<Serviceapi> serviceapi = null;

  /**
   * Gets or Sets type
   */
  public enum TypeEnum {
    POSTMAN("POSTMAN"),
    
    VIRTUALAN("VIRTUALAN"),
    
    EXCEL("EXCEL");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("type")
  private TypeEnum type;

  @JsonProperty("message")
  private String message;

  @JsonProperty("error")
  private String error;

  public Test testid(UUID testid) {
    this.testid = testid;
    return this;
  }

  /**
   * Get testid
   * @return testid
  */
  @ApiModelProperty(value = "")

  @Valid

  public UUID getTestid() {
    return testid;
  }

  public void setTestid(UUID testid) {
    this.testid = testid;
  }

  public Test creationtime(String creationtime) {
    this.creationtime = creationtime;
    return this;
  }

  /**
   * Get creationtime
   * @return creationtime
  */
  @ApiModelProperty(value = "")


  public String getCreationtime() {
    return creationtime;
  }

  public void setCreationtime(String creationtime) {
    this.creationtime = creationtime;
  }

  public Test lastexecution(String lastexecution) {
    this.lastexecution = lastexecution;
    return this;
  }

  /**
   * Get lastexecution
   * @return lastexecution
  */
  @ApiModelProperty(value = "")


  public String getLastexecution() {
    return lastexecution;
  }

  public void setLastexecution(String lastexecution) {
    this.lastexecution = lastexecution;
  }

  public Test load(List<String> load) {
    this.load = load;
    return this;
  }

  public Test addLoadItem(String loadItem) {
    if (this.load == null) {
      this.load = new ArrayList<>();
    }
    this.load.add(loadItem);
    return this;
  }

  /**
   * Get load
   * @return load
  */
  @ApiModelProperty(value = "")


  public List<String> getLoad() {
    return load;
  }

  public void setLoad(List<String> load) {
    this.load = load;
  }

  public Test serviceapi(List<Serviceapi> serviceapi) {
    this.serviceapi = serviceapi;
    return this;
  }

  public Test addServiceapiItem(Serviceapi serviceapiItem) {
    if (this.serviceapi == null) {
      this.serviceapi = new ArrayList<>();
    }
    this.serviceapi.add(serviceapiItem);
    return this;
  }

  /**
   * Get serviceapi
   * @return serviceapi
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<Serviceapi> getServiceapi() {
    return serviceapi;
  }

  public void setServiceapi(List<Serviceapi> serviceapi) {
    this.serviceapi = serviceapi;
  }

  public Test type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  */
  @ApiModelProperty(value = "")


  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public Test message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  */
  @ApiModelProperty(value = "")


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Test error(String error) {
    this.error = error;
    return this;
  }

  /**
   * Get error
   * @return error
  */
  @ApiModelProperty(value = "")


  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Test test = (Test) o;
    return Objects.equals(this.testid, test.testid) &&
        Objects.equals(this.creationtime, test.creationtime) &&
        Objects.equals(this.lastexecution, test.lastexecution) &&
        Objects.equals(this.load, test.load) &&
        Objects.equals(this.serviceapi, test.serviceapi) &&
        Objects.equals(this.type, test.type) &&
        Objects.equals(this.message, test.message) &&
        Objects.equals(this.error, test.error);
  }

  @Override
  public int hashCode() {
    return Objects.hash(testid, creationtime, lastexecution, load, serviceapi, type, message, error);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Test {\n");
    
    sb.append("    testid: ").append(toIndentedString(testid)).append("\n");
    sb.append("    creationtime: ").append(toIndentedString(creationtime)).append("\n");
    sb.append("    lastexecution: ").append(toIndentedString(lastexecution)).append("\n");
    sb.append("    load: ").append(toIndentedString(load)).append("\n");
    sb.append("    serviceapi: ").append(toIndentedString(serviceapi)).append("\n");
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

