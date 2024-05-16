/*
 * Cat Facts API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package pl.zajavka.infrastructure.catFacts.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * CatFact
 */
@JsonPropertyOrder({
  CatFact.JSON_PROPERTY_FACT,
  CatFact.JSON_PROPERTY_LENGTH
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-05-16T21:17:38.124590300+02:00[Europe/Warsaw]", comments = "Generator version: 7.5.0")
public class CatFact implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_FACT = "fact";
  private String fact;

  public static final String JSON_PROPERTY_LENGTH = "length";
  private Integer length;

  public CatFact() {
  }

  public CatFact fact(String fact) {
    
    this.fact = fact;
    return this;
  }

   /**
   * Fact
   * @return fact
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_FACT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getFact() {
    return fact;
  }


  @JsonProperty(JSON_PROPERTY_FACT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setFact(String fact) {
    this.fact = fact;
  }


  public CatFact length(Integer length) {
    
    this.length = length;
    return this;
  }

   /**
   * Length
   * @return length
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_LENGTH)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getLength() {
    return length;
  }


  @JsonProperty(JSON_PROPERTY_LENGTH)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setLength(Integer length) {
    this.length = length;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CatFact catFact = (CatFact) o;
    return Objects.equals(this.fact, catFact.fact) &&
        Objects.equals(this.length, catFact.length);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fact, length);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CatFact {\n");
    sb.append("    fact: ").append(toIndentedString(fact)).append("\n");
    sb.append("    length: ").append(toIndentedString(length)).append("\n");
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

