package pl.zajavka.infrastructure.catFacts.api;

import pl.zajavka.infrastructure.catFacts.ApiClient;

import pl.zajavka.infrastructure.catFacts.model.Breed;

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

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-05-15T21:58:34.722377300+02:00[Europe/Warsaw]", comments = "Generator version: 7.5.0")
public class BreedsApi {
    private ApiClient apiClient;

    public BreedsApi() {
        this(new ApiClient());
    }

    @Autowired
    public BreedsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get a list of breeds
     * Returns a a list of breeds
     * <p><b>200</b> - successful operation
     * @param limit limit the amount of results returned
     * @return List&lt;Breed&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getBreedsRequestCreation(Long limit) throws WebClientResponseException {
        Object postBody = null;
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        
        final String[] localVarAccepts = { 
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<Breed> localVarReturnType = new ParameterizedTypeReference<Breed>() {};
        return apiClient.invokeAPI("/breeds", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get a list of breeds
     * Returns a a list of breeds
     * <p><b>200</b> - successful operation
     * @param limit limit the amount of results returned
     * @return List&lt;Breed&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Flux<Breed> getBreeds(Long limit) throws WebClientResponseException {
        ParameterizedTypeReference<Breed> localVarReturnType = new ParameterizedTypeReference<Breed>() {};
        return getBreedsRequestCreation(limit).bodyToFlux(localVarReturnType);
    }

    /**
     * Get a list of breeds
     * Returns a a list of breeds
     * <p><b>200</b> - successful operation
     * @param limit limit the amount of results returned
     * @return ResponseEntity&lt;List&lt;Breed&gt;&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<List<Breed>>> getBreedsWithHttpInfo(Long limit) throws WebClientResponseException {
        ParameterizedTypeReference<Breed> localVarReturnType = new ParameterizedTypeReference<Breed>() {};
        return getBreedsRequestCreation(limit).toEntityList(localVarReturnType);
    }

    /**
     * Get a list of breeds
     * Returns a a list of breeds
     * <p><b>200</b> - successful operation
     * @param limit limit the amount of results returned
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getBreedsWithResponseSpec(Long limit) throws WebClientResponseException {
        return getBreedsRequestCreation(limit);
    }
}
