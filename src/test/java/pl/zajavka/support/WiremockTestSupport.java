package pl.zajavka.support;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public interface WiremockTestSupport {

    default void stubForCatFact(WireMockServer wireMockServer) {
        wireMockServer.stubFor(
                get(urlPathEqualTo("/fact"))
                        .withQueryParam("max_length", equalTo("127"))
                        .willReturn(aResponse()
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .withBodyFile("wiremock/catFact.json")));
    }

}
