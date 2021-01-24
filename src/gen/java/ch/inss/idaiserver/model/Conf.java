package ch.inss.idaiserver.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Conf
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-01-24T15:45:18.941236+01:00[Europe/Zurich]")
public class Conf   {
  @JsonProperty("serverUrl")
  private String serverUrl;

  public Conf serverUrl(String serverUrl) {
    this.serverUrl = serverUrl;
    return this;
  }

  /**
   * Get serverUrl
   * @return serverUrl
  */
  @ApiModelProperty(value = "")


  public String getServerUrl() {
    return serverUrl;
  }

  public void setServerUrl(String serverUrl) {
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

