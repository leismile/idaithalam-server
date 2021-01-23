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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-01-23T13:14:27.767932+01:00[Europe/Zurich]")
public class Report   {
  @JsonProperty("testid")
  private UUID testid;

  @JsonProperty("success")
  private Boolean success;

  @JsonProperty("sessionNr")
  private Integer sessionNr;

  @JsonProperty("testExecuted")
  private Boolean testExecuted;

  @JsonProperty("skipResponseValidation")
  private Boolean skipResponseValidation;

  @JsonProperty("creationTime")
  private String creationTime;

  @JsonProperty("startTime")
  private String startTime;

  @JsonProperty("endTime")
  private String endTime;

  @JsonProperty("durationSeconds")
  private String durationSeconds;

  @JsonProperty("linkToTeature")
  private String linkToTeature;

  @JsonProperty("linkToReport")
  private String linkToReport;

  @JsonProperty("linkToProperties")
  private String linkToProperties;

  @JsonProperty("linkToSessions")
  private String linkToSessions;

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

  public Report sessionNr(Integer sessionNr) {
    this.sessionNr = sessionNr;
    return this;
  }

  /**
   * Get sessionNr
   * @return sessionNr
  */
  @ApiModelProperty(value = "")


  public Integer getSessionNr() {
    return sessionNr;
  }

  public void setSessionNr(Integer sessionNr) {
    this.sessionNr = sessionNr;
  }

  public Report testExecuted(Boolean testExecuted) {
    this.testExecuted = testExecuted;
    return this;
  }

  /**
   * Get testExecuted
   * @return testExecuted
  */
  @ApiModelProperty(value = "")


  public Boolean getTestExecuted() {
    return testExecuted;
  }

  public void setTestExecuted(Boolean testExecuted) {
    this.testExecuted = testExecuted;
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

  public Report creationTime(String creationTime) {
    this.creationTime = creationTime;
    return this;
  }

  /**
   * Get creationTime
   * @return creationTime
  */
  @ApiModelProperty(value = "")


  public String getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(String creationTime) {
    this.creationTime = creationTime;
  }

  public Report startTime(String startTime) {
    this.startTime = startTime;
    return this;
  }

  /**
   * Get startTime
   * @return startTime
  */
  @ApiModelProperty(value = "")


  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public Report endTime(String endTime) {
    this.endTime = endTime;
    return this;
  }

  /**
   * Get endTime
   * @return endTime
  */
  @ApiModelProperty(value = "")


  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public Report durationSeconds(String durationSeconds) {
    this.durationSeconds = durationSeconds;
    return this;
  }

  /**
   * Get durationSeconds
   * @return durationSeconds
  */
  @ApiModelProperty(value = "")


  public String getDurationSeconds() {
    return durationSeconds;
  }

  public void setDurationSeconds(String durationSeconds) {
    this.durationSeconds = durationSeconds;
  }

  public Report linkToTeature(String linkToTeature) {
    this.linkToTeature = linkToTeature;
    return this;
  }

  /**
   * Get linkToTeature
   * @return linkToTeature
  */
  @ApiModelProperty(value = "")


  public String getLinkToTeature() {
    return linkToTeature;
  }

  public void setLinkToTeature(String linkToTeature) {
    this.linkToTeature = linkToTeature;
  }

  public Report linkToReport(String linkToReport) {
    this.linkToReport = linkToReport;
    return this;
  }

  /**
   * Get linkToReport
   * @return linkToReport
  */
  @ApiModelProperty(value = "")


  public String getLinkToReport() {
    return linkToReport;
  }

  public void setLinkToReport(String linkToReport) {
    this.linkToReport = linkToReport;
  }

  public Report linkToProperties(String linkToProperties) {
    this.linkToProperties = linkToProperties;
    return this;
  }

  /**
   * Get linkToProperties
   * @return linkToProperties
  */
  @ApiModelProperty(value = "")


  public String getLinkToProperties() {
    return linkToProperties;
  }

  public void setLinkToProperties(String linkToProperties) {
    this.linkToProperties = linkToProperties;
  }

  public Report linkToSessions(String linkToSessions) {
    this.linkToSessions = linkToSessions;
    return this;
  }

  /**
   * Get linkToSessions
   * @return linkToSessions
  */
  @ApiModelProperty(value = "")


  public String getLinkToSessions() {
    return linkToSessions;
  }

  public void setLinkToSessions(String linkToSessions) {
    this.linkToSessions = linkToSessions;
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
        Objects.equals(this.sessionNr, report.sessionNr) &&
        Objects.equals(this.testExecuted, report.testExecuted) &&
        Objects.equals(this.skipResponseValidation, report.skipResponseValidation) &&
        Objects.equals(this.creationTime, report.creationTime) &&
        Objects.equals(this.startTime, report.startTime) &&
        Objects.equals(this.endTime, report.endTime) &&
        Objects.equals(this.durationSeconds, report.durationSeconds) &&
        Objects.equals(this.linkToTeature, report.linkToTeature) &&
        Objects.equals(this.linkToReport, report.linkToReport) &&
        Objects.equals(this.linkToProperties, report.linkToProperties) &&
        Objects.equals(this.linkToSessions, report.linkToSessions) &&
        Objects.equals(this.message, report.message) &&
        Objects.equals(this.error, report.error);
  }

  @Override
  public int hashCode() {
    return Objects.hash(testid, success, sessionNr, testExecuted, skipResponseValidation, creationTime, startTime, endTime, durationSeconds, linkToTeature, linkToReport, linkToProperties, linkToSessions, message, error);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Report {\n");
    
    sb.append("    testid: ").append(toIndentedString(testid)).append("\n");
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
    sb.append("    sessionNr: ").append(toIndentedString(sessionNr)).append("\n");
    sb.append("    testExecuted: ").append(toIndentedString(testExecuted)).append("\n");
    sb.append("    skipResponseValidation: ").append(toIndentedString(skipResponseValidation)).append("\n");
    sb.append("    creationTime: ").append(toIndentedString(creationTime)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
    sb.append("    durationSeconds: ").append(toIndentedString(durationSeconds)).append("\n");
    sb.append("    linkToTeature: ").append(toIndentedString(linkToTeature)).append("\n");
    sb.append("    linkToReport: ").append(toIndentedString(linkToReport)).append("\n");
    sb.append("    linkToProperties: ").append(toIndentedString(linkToProperties)).append("\n");
    sb.append("    linkToSessions: ").append(toIndentedString(linkToSessions)).append("\n");
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

