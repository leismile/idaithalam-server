package ch.inss.idaiserver.model;

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
 * Conf
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-01-24T13:26:32.313525+01:00[Europe/Zurich]")
public class Conf   {
  @JsonProperty("serverUrl")
  @Valid
  private List<String> serverUrl = null;

  public Conf serverUrl(List<String> serverUrl) {
    this.serverUrl = serverUrl;
    return this;
  }

  public Conf addServerUrlItem(String serverUrlItem) {
    if (this.serverUrl == null) {
      this.serverUrl = new ArrayList<>();
    }
    this.serverUrl.add(serverUrlItem);
    return this;
  }

  /**
   * Get serverUrl
   * @return serverUrl
  */
  @ApiModelProperty(value = "")


  public List<String> getServerUrl() {
    return serverUrl;
  }

  public void setServerUrl(List<String> serverUrl) {
    this.serverUrl = serverUrl;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Conf conf = (Conf) o;
    return Objects.equals(this.serverUrl, conf.serverUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serverUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Conf {\n");
    
    sb.append("    serverUrl: ").append(toIndentedString(serverUrl)).append("\n");
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

