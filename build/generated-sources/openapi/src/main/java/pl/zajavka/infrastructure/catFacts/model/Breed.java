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
 * Breed
 */
@JsonPropertyOrder({
  Breed.JSON_PROPERTY_BREED,
  Breed.JSON_PROPERTY_COUNTRY,
  Breed.JSON_PROPERTY_ORIGIN,
  Breed.JSON_PROPERTY_COAT,
  Breed.JSON_PROPERTY_PATTERN
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-05-16T21:17:38.124590300+02:00[Europe/Warsaw]", comments = "Generator version: 7.5.0")
public class Breed implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_BREED = "breed";
  private String breed;

  public static final String JSON_PROPERTY_COUNTRY = "country";
  private String country;

  public static final String JSON_PROPERTY_ORIGIN = "origin";
  private String origin;

  public static final String JSON_PROPERTY_COAT = "coat";
  private String coat;

  public static final String JSON_PROPERTY_PATTERN = "pattern";
  private String pattern;

  public Breed() {
  }

  public Breed breed(String breed) {
    
    this.breed = breed;
    return this;
  }

   /**
   * Breed
   * @return breed
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_BREED)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getBreed() {
    return breed;
  }


  @JsonProperty(JSON_PROPERTY_BREED)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setBreed(String breed) {
    this.breed = breed;
  }


  public Breed country(String country) {
    
    this.country = country;
    return this;
  }

   /**
   * Country
   * @return country
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_COUNTRY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCountry() {
    return country;
  }


  @JsonProperty(JSON_PROPERTY_COUNTRY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCountry(String country) {
    this.country = country;
  }


  public Breed origin(String origin) {
    
    this.origin = origin;
    return this;
  }

   /**
   * Origin
   * @return origin
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ORIGIN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getOrigin() {
    return origin;
  }


  @JsonProperty(JSON_PROPERTY_ORIGIN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setOrigin(String origin) {
    this.origin = origin;
  }


  public Breed coat(String coat) {
    
    this.coat = coat;
    return this;
  }

   /**
   * Coat
   * @return coat
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_COAT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCoat() {
    return coat;
  }


  @JsonProperty(JSON_PROPERTY_COAT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCoat(String coat) {
    this.coat = coat;
  }


  public Breed pattern(String pattern) {
    
    this.pattern = pattern;
    return this;
  }

   /**
   * Pattern
   * @return pattern
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PATTERN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getPattern() {
    return pattern;
  }


  @JsonProperty(JSON_PROPERTY_PATTERN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setPattern(String pattern) {
    this.pattern = pattern;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Breed breed = (Breed) o;
    return Objects.equals(this.breed, breed.breed) &&
        Objects.equals(this.country, breed.country) &&
        Objects.equals(this.origin, breed.origin) &&
        Objects.equals(this.coat, breed.coat) &&
        Objects.equals(this.pattern, breed.pattern);
  }

  @Override
  public int hashCode() {
    return Objects.hash(breed, country, origin, coat, pattern);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Breed {\n");
    sb.append("    breed: ").append(toIndentedString(breed)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    origin: ").append(toIndentedString(origin)).append("\n");
    sb.append("    coat: ").append(toIndentedString(coat)).append("\n");
    sb.append("    pattern: ").append(toIndentedString(pattern)).append("\n");
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

