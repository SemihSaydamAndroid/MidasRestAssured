package org.example.step;


import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.api.RestApi;
import org.example.cache.CacheHelper;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * @author Semih Saydam
 * @since 24.01.2022
 */
public class RestDefinitions {
    private final Logger logger = LogManager.getLogger(RestDefinitions.class);

    private final RestApi restApi = new RestApi();
    private final CacheHelper cacheHelper = new CacheHelper();

    @Given("Create new rest api request.")
    public void createNewRequest(){
        restApi.createRequest();
    }

    @Given("^\"(.*)\" base url add for the rest api.$")
    public void addBaseUrlToReq(String url) throws Exception {
        restApi.addBaseUrl(url);
    }

    @Given("^\"(.*)\" base path add for the rest api.$")
    public void addBasePathToReq(String url) throws Exception {
        restApi.addBasePath(url);
    }

    @Given("^Add header with '(.*)' key and '(.*)' value$")
    public void addHeaderToReq(String key, String value) throws  Exception {
        restApi.addHeader(key,value);
    }

    @Given("^Add payload from '(.*)' file.$")
    public void addPayloadToRestApiFromFile(String filePath) throws Exception{
        restApi.addPayload(Files.readAllBytes(Paths.get(filePath)));
    }

    @Given("^Add '(.*)' body for request.$")
    public void addPayloadRestApi(String body) throws Exception {
        restApi.addPayload(body);
    }

    @Given("^Send POST for rest api request.$")
    public void postRequest() throws Exception {
        restApi.post();
    }

    @Given("^Send POST for rest api request for \"(.*)\".$")
    public void postRequest(String url) throws Exception {
        restApi.post(url);
    }

    @Given("^Send GET for rest api request.$")
    public void getRequest() throws Exception {
        restApi.get();
    }

    @Given("^Send GET for rest api request for \"(.*)\".$")
    public void getRequest(String url) throws Exception {
        restApi.get(url);
    }

    @Given("^Check response status code is '(.*)'.$")
    public void checkSC(int statusCode) throws Exception {
        restApi.checkStatusCode(statusCode);
    }

    @Given("^Print Response body.$")
    public void getRespBody() throws Exception {
        restApi.getResponseBody();
    }

    @Given("^Response json '(.*)' value is equal to \"(.*)\" control .$")
    public void checkJsonValue(String key, String expected) throws Exception{
        String actual = String.valueOf(restApi.getJsonValue(key));
        assertEquals(expected, actual);
    }

    @Given("^Response xml '(.*)' value is equal to \"(.*)\" control .$")
    public void checkXmlValue(String key, String expected) throws Exception{
        String actual = String.valueOf(restApi.getXmlValue(key));
        assertEquals(expected, actual);
    }

    @Given("^Add query param with '(.*)' key and '(.*)' value.$")
    public void addQueryParam(String key, String value) throws Exception{
        restApi.addQueryParam(key, value);
    }

    @Given("^Add path param with '(.*)' key and '(.*)' value.$")
    public void addPathParam(String key, String value) throws Exception{
        restApi.addPathParam(key, value);
    }

    @Given("^Add form param with '(.*)' key and '(.*)' value.$")
    public void addFormParam(String key, String value) throws Exception{
        restApi.addFormParam(key, value);
    }

    @Given("^Save rest api response '(.*)' json key value save to '(.*)' variable.$")
    public void saveVariable(String jsonKey, String key) throws Exception{
        cacheHelper.globalVariable().put(key, restApi.getJsonValue(jsonKey));
        logger.info("Response json value is '{}' saved with name '{}'", jsonKey, key);
    }

    @Given("^Add path param with '(.*)' key and '(.*)' saved variable value.$")
    public void pathParamWithVariable(String key, String cacheKey) throws Exception{
        restApi.addPathParam(key, cacheHelper.globalVariable().get(cacheKey).toString());
    }
}
