package pl.zajavka.infrastructure.catFacts.api;

import pl.zajavka.infrastructure.catFacts.ApiClient;

import pl.zajavka.infrastructure.catFacts.model.CatFact;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-05-17T22:33:15.865163500+02:00[Europe/Warsaw]", comments = "Generator version: 7.5.0")
public class FactsApi {
    private ApiClient apiClient;

    public FactsApi() {
        this(new ApiClient());
    }

    @Autowired
    public FactsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get a list of facts
     * Returns a a list of facts
     * <p><b>200</b> - successful operation
     * @param maxLength maximum length of returned fact
     * @param limit limit the amount of results returned
     * @return List&lt;CatFact&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getFactsRequestCreation(Long maxLength, Long limit) throws WebClientResponseException {
        Object postBody = null;
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "max_length", maxLength));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        
        final String[] localVarAccepts = { 
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<CatFact> localVarReturnType = new ParameterizedTypeReference<CatFact>() {};
        return apiClient.invokeAPI("/facts", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get a list of facts
     * Returns a a list of facts
     * <p><b>200</b> - successful operation
     * @param maxLength maximum length of returned fact
     * @param limit limit the amount of results returned
     * @return List&lt;CatFact&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Flux<CatFact> getFacts(Long maxLength, Long limit) throws WebClientResponseException {
        ParameterizedTypeReference<CatFact> localVarReturnType = new ParameterizedTypeReference<CatFact>() {};
        return getFactsRequestCreation(maxLength, limit).bodyToFlux(localVarReturnType);
    }

    /**
     * Get a list of facts
     * Returns a a list of facts
     * <p><b>200</b> - successful operation
     * @param maxLength maximum length of returned fact
     * @param limit limit the amount of results returned
     * @return ResponseEntity&lt;List&lt;CatFact&gt;&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<List<CatFact>>> getFactsWithHttpInfo(Long maxLength, Long limit) throws WebClientResponseException {
        ParameterizedTypeReference<CatFact> localVarReturnType = new ParameterizedTypeReference<CatFact>() {};
        return getFactsRequestCreation(maxLength, limit).toEntityList(localVarReturnType);
    }

    /**
     * Get a list of facts
     * Returns a a list of facts
     * <p><b>200</b> - successful operation
     * @param maxLength maximum length of returned fact
     * @param limit limit the amount of results returned
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getFactsWithResponseSpec(Long maxLength, Long limit) throws WebClientResponseException {
        return getFactsRequestCreation(maxLength, limit);
    }
    /**
     * Get Random Fact
     * Returns a random fact
     * <p><b>200</b> - successful operation
     * <p><b>404</b> - Fact not found
     * @param maxLength maximum length of returned fact
     * @return CatFact
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getRandomFactRequestCreation(Long maxLength) throws WebClientResponseException {
        Object postBody = null;
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "max_length", maxLength));
        
        final String[] localVarAccepts = { 
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<CatFact> localVarReturnType = new ParameterizedTypeReference<CatFact>() {};
        return apiClient.invokeAPI("/fact", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Random Fact
     * Returns a random fact
     * <p><b>200</b> - successful operation
     * <p><b>404</b> - Fact not found
     * @param maxLength maximum length of returned fact
     * @return CatFact
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<CatFact> getRandomFact(Long maxLength) throws WebClientResponseException {
        ParameterizedTypeReference<CatFact> localVarReturnType = new ParameterizedTypeReference<CatFact>() {};
        return getRandomFactRequestCreation(maxLength).bodyToMono(localVarReturnType);
    }

    /**
     * Get Random Fact
     * Returns a random fact
     * <p><b>200</b> - successful operation
     * <p><b>404</b> - Fact not found
     * @param maxLength maximum length of returned fact
     * @return ResponseEntity&lt;CatFact&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<CatFact>> getRandomFactWithHttpInfo(Long maxLength) throws WebClientResponseException {
        ParameterizedTypeReference<CatFact> localVarReturnType = new ParameterizedTypeReference<CatFact>() {};
        return getRandomFactRequestCreation(maxLength).toEntity(localVarReturnType);
    }

    /**
     * Get Random Fact
     * Returns a random fact
     * <p><b>200</b> - successful operation
     * <p><b>404</b> - Fact not found
     * @param maxLength maximum length of returned fact
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getRandomFactWithResponseSpec(Long maxLength) throws WebClientResponseException {
        return getRandomFactRequestCreation(maxLength);
    }
}
