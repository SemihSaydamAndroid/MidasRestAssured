package org.example.unitTests;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import org.example.api.RestApi;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * @author Semih Saydam
 * @since 24.01.2022
 *
 * @see org.example.api.RestApi
 */
public class RestApiTest {
    public static final String TEST_BASEURI_MOCK = "http://0.0.0.0:9999/test";
    public static final String TEST_BASEURI_MOCK_BASE = "http://0.0.0.0:9999";
    public static final String TEST_BASEURI_MOCK_PATH = "/test";
    public static final String TEST_JSON = "{test:'test'}";
    public static final String TEST_PARAM = "TEST";
    public static final String TEST_PARAM_PATH = "/test";
    public static final String TEST_REQUEST_BODY = "{\"status\":\"success\",\"message\":\"success done\"}";
    public static final String TEST_PARAM_VALUE = "TEST_VALUE";
    public static final String TEST_CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";

    private RestApi restApi;

    @Rule
    public ExpectedException expectedRule = ExpectedException.none();

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(9999);

    @Test
    public void exactUrlOnlyTest() throws Exception {
        stubFor(get(urlEqualTo(TEST_PARAM_PATH))
                .willReturn(aResponse()
                        .withBody(TEST_REQUEST_BODY)
                        .withStatus(200)
                        .withHeader(TEST_CONTENT_TYPE, APPLICATION_JSON)));
        stubFor(delete(TEST_PARAM_PATH).willReturn(aResponse().withStatus(201)));

        restApi = new RestApi();
        restApi.createRequest();
        restApi.get(TEST_BASEURI_MOCK);
        restApi.checkStatusCode(200);

    }

    @Test
    public void getJsonValueTest() throws Exception {
        stubFor(get(urlEqualTo(TEST_PARAM_PATH))
                .willReturn(aResponse()
                        .withBody(TEST_REQUEST_BODY)
                        .withStatus(200)
                        .withHeader(TEST_CONTENT_TYPE, APPLICATION_JSON)));
        stubFor(delete(TEST_PARAM_PATH).willReturn(aResponse().withStatus(201)));

        restApi = new RestApi();
        restApi.createRequest();
        restApi.get(TEST_BASEURI_MOCK);
        assertEquals("success", restApi.getJsonValue("status").toString());
        restApi.checkStatusCode(200);
    }

    @Test
    public void postRequestWithUrlTest() throws Exception {
        stubFor(post(urlEqualTo(TEST_PARAM_PATH))
                .willReturn(aResponse()
                        .withBody(TEST_REQUEST_BODY)
                        .withStatus(200)
                        .withHeader(TEST_CONTENT_TYPE, APPLICATION_JSON)));
        stubFor(delete(TEST_PARAM_PATH).willReturn(aResponse().withStatus(201)));

        restApi = new RestApi();
        restApi.createRequest();

        JSONObject requestParams = new JSONObject();
        requestParams.put("name","test");
        requestParams.put("job","test1");
        restApi.addPayload(requestParams.toJSONString());
        Response response = restApi.post(TEST_BASEURI_MOCK);
        assertNotNull(response);
        assertTrue(response.getBody().prettyPrint().contains("success"));

    }
}
