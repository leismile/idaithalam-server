package ch.inss.idaiserver.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Report
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-01-23T06:28:03.267371+01:00[Europe/Zurich]")
public class Report   {
  @JsonProperty("testid")
  private UUID testid;

  @JsonProperty("success")
  private Boolean success;

  @JsonProperty("testexecuted")
  private Boolean testexecuted;

  @JsonProperty("skipResponseValidation")
  private Boolean skipResponseValidation;

  @JsonProperty("creationtime")
  private String creationtime;

  @JsonProperty("executiontime")
  private String executiontime;

  @JsonProperty("linktofeature")
  private String linktofeature;

  @JsonProperty("linktoreport")
  private String linktoreport;

  @JsonProperty("linktoproperties")
  private String linktoproperties;

  @JsonProperty("message")
  private String message;

  @JsonProperty("error")
  private String error;

  public Report testid(UUID testid) {
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

  public Report success(Boolean success) {
    this.success = success;
    return this;
  }

  /**
   * Get success
   * @return success
  */
  @ApiModelProperty(value = "")


  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public Report testexecuted(Boolean testexecuted) {
    this.testexecuted = testexecuted;
    return this;
  }

  /**
   * Get testexecuted
   * @return testexecuted
  */
  @ApiModelProperty(value = "")


  public Boolean getTestexecuted() {
    return testexecuted;
  }

  public void setTestexecuted(Boolean testexecuted) {
    this.testexecuted = testexecuted;
  }

  public Report skipResponseValidation(Boolean skipResponseValidation) {
    this.skipResponseValidation = skipResponseValidation;
    return this;
  }

  /**
   * Get skipResponseValidation
   * @return skipResponseValidation
  */
  @ApiModelProperty(value = "")


  public Boolean getSkipResponseValidation() {
    return skipResponseValidation;
  }

  public void setSkipResponseValidation(Boolean skipResponseValidation) {
    this.skipResponseValidation = skipResponseValidation;
  }

  public Report creationtime(String creationtime) {
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

  public Report executiontime(String executiontime) {
    this.executiontime = executiontime;
    return this;
  }

  /**
   * Get executiontime
   * @return executiontime
  */
  @ApiModelProperty(value = "")


  public String getExecutiontime() {
    return executiontime;
  }

  public void setExecutiontime(String executiontime) {
    this.executiontime = executiontime;
  }

  public Report linktofeature(String linktofeature) {
    this.linktofeature = linktofeature;
    return this;
  }

  /**
   * Get linktofeature
   * @return linktofeature
  */
  @ApiModelProperty(value = "")


  public String getLinktofeature() {
    return linktofeature;
  }

  public void setLinktofeature(String linktofeature) {
    this.linktofeature = linktofeature;
  }

  public Report linktoreport(String linktoreport) {
    this.linktoreport = linktoreport;
    return this;
  }

  /**
   * Get linktoreport
   * @return linktoreport
  */
  @ApiModelProperty(value = "")


  public String getLinktoreport() {
    return linktoreport;
  }

  public void setLinktoreport(String linktoreport) {
    this.linktoreport = linktoreport;
  }

  public Report linktoproperties(String linktoproperties) {
    this.linktoproperties = linktoproperties;
    return this;
  }

  /**
   * Get linktoproperties
   * @return linktoproperties
  */
  @ApiModelProperty(value = "")


  public String getLinktoproperties() {
    return linktoproperties;
  }

  public void setLinktoproperties(String linktoproperties) {
    this.linktoproperties = linktoproperties;
  }

  public Report message(String message) {
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

  public Report error(String error) {
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
    Report report = (Report) o;
    return Objects.equals(this.testid, report.testid) &&
        Objects.equals(this.success, report.success) &&
        Objects.equals(this.testexecuted, report.testexecuted) &&
        Objects.equals(this.skipResponseValidation, report.skipResponseValidation) &&
        Objects.equals(this.creationtime, report.creationtime) &&
        Objects.equals(this.executiontime, report.executiontime) &&
        Objects.equals(this.linktofeature, report.linktofeature) &&
        Objects.equals(this.linktoreport, report.linktoreport) &&
        Objects.equals(this.linktoproperties, report.linktoproperties) &&
        Objects.equals(this.message, report.message) &&
        Objects.equals(this.error, report.error);
  }

  @Override
  public int hashCode() {
    return Objects.hash(testid, success, testexecuted, skipResponseValidation, creationtime, executiontime, linktofeature, linktoreport, linktoproperties, message, error);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Report {\n");
    
    sb.append("    testid: ").append(toIndentedString(testid)).append("\n");
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
    sb.append("    testexecuted: ").append(toIndentedString(testexecuted)).append("\n");
    sb.append("    skipResponseValidation: ").append(toIndentedString(skipResponseValidation)).append("\n");
    sb.append("    creationtime: ").append(toIndentedString(creationtime)).append("\n");
    sb.append("    executiontime: ").append(toIndentedString(executiontime)).append("\n");
    sb.append("    linktofeature: ").append(toIndentedString(linktofeature)).append("\n");
    sb.append("    linktoreport: ").append(toIndentedString(linktoreport)).append("\n");
    sb.append("    linktoproperties: ").append(toIndentedString(linktoproperties)).append("\n");
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

