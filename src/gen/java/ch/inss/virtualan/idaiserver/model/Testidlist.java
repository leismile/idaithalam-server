package ch.inss.virtualan.idaiserver.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * List of all test IDs.
 */
@ApiModel(description = "List of all test IDs.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-02-09T19:32:47.309953+01:00[Europe/Zurich]")
public class Testidlist   {
  @JsonProperty("serverUrl")
  @Valid
  private List<String> serverUrl = null;

  @JsonProperty("idList")
  @Valid
  private List<String> idList = null;

  public Testidlist serverUrl(List<String> serverUrl) {
    this.serverUrl = serverUrl;
    return this;
  }

  public Testidlist addServerUrlItem(String serverUrlItem) {
    if (this.serverUrl == null) {
      this.serverUrl = new ArrayList<>();
    }
    this.serverUrl.add(serverUrlItem);
    return this;
  }

  /**
   * Array of all testlinks.
   * @return serverUrl
  */
  @ApiModelProperty(value = "Array of all testlinks.")


  public List<String> getServerUrl() {
    return serverUrl;
  }

  public void setServerUrl(List<String> serverUrl) {
    this.serverUrl = serverUrl;
  }

  public Testidlist idList(List<String> idList) {
    this.idList = idList;
    return this;
  }

  public Testidlist addIdListItem(String idListItem) {
    if (this.idList == null) {
      this.idList = new ArrayList<>();
    }
    this.idList.add(idListItem);
    return this;
  }

  /**
   * Array of all test IDs.
   * @return idList
  */
  @ApiModelProperty(value = "Array of all test IDs.")


  public List<String> getIdList() {
    return idList;
  }

  public void setIdList(List<String> idList) {
    this.idList = idList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Testidlist testidlist = (Testidlist) o;
    return Objects.equals(this.serverUrl, testidlist.serverUrl) &&
        Objects.equals(this.idList, testidlist.idList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serverUrl, idList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Testidlist {\n");
    
    sb.append("    serverUrl: ").append(toIndentedString(serverUrl)).append("\n");
    sb.append("    idList: ").append(toIndentedString(idList)).append("\n");
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

