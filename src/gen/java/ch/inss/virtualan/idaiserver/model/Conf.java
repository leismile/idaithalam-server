package ch.inss.virtualan.idaiserver.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-20T18:25:52.218752+02:00[Europe/Zurich]")
public class Conf   {
  @JsonProperty("serverUrls")
  @Valid
  private List<String> serverUrls = null;

  /**
   * Gets or Sets dataType
   */
  public enum DataTypeEnum {
    POSTMAN("POSTMAN"),
    
    OPENAPI("OPENAPI"),
    
    EXCEL("EXCEL"),
    
    VIRTUALAN("VIRTUALAN");

    private String value;

    DataTypeEnum(String value) {
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
    public static DataTypeEnum fromValue(String value) {
      for (DataTypeEnum b : DataTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("dataType")
  private DataTypeEnum dataType;

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

  public Conf dataType(DataTypeEnum dataType) {
    this.dataType = dataType;
    return this;
  }

  /**
   * Get dataType
   * @return dataType
  */
  @ApiModelProperty(value = "")


  public DataTypeEnum getDataType() {
    return dataType;
  }

  public void setDataType(DataTypeEnum dataType) {
    this.dataType = dataType;
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
    return Objects.equals(this.serverUrls, conf.serverUrls) &&
        Objects.equals(this.dataType, conf.dataType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serverUrls, dataType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Conf {\n");
    
    sb.append("    serverUrls: ").append(toIndentedString(serverUrls)).append("\n");
    sb.append("    dataType: ").append(toIndentedString(dataType)).append("\n");
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

