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
 * Conf
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-02-19T21:11:18.557400+01:00[Europe/Zurich]")
public class Conf   {
  @JsonProperty("serverUrls")
  @Valid
  private List<String> serverUrls = null;

  public Conf serverUrls(List<String> serverUrls) {
    this.serverUrls = serverUrls;
    return this;
  }

  public Conf addServerUrlsItem(String serverUrlsItem) {
    if (this.serverUrls == null) {
      this.serverUrls = new ArrayList<>();
    }
    this.serverUrls.add(serverUrlsItem);
    return this;
  }

  /**
   * Get serverUrls
   * @return serverUrls
  */
  @ApiModelProperty(value = "")


  public List<String> getServerUrls() {
    return serverUrls;
  }

  public void setServerUrls(List<String> serverUrls) {
    this.serverUrls = serverUrls;
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
    return Objects.equals(this.serverUrls, conf.serverUrls);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serverUrls);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Conf {\n");
    
    sb.append("    serverUrls: ").append(toIndentedString(serverUrls)).append("\n");
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

