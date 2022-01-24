package org.example.api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * @author Semih Saydam
 * @since 24.01.2022
 */

public class RestApi implements IRest {

    private final Logger logger = LogManager.getLogger(RestApi.class);

    private RequestSpecification request;
    private Response response;
    private QueryableRequestSpecification queryRequest;

    @Override
    public void createRequest(){
        setRequest(RestAssured.given());
        logger.info("Created new rest api request");
    }

    private void checkReqDefine() throws Exception {
        if (request == null) {
            logger.error("Can't find defined request");
            throw new Exception();
        }
    }

    @Override
    public Response post(String url) throws Exception{
        checkReqDefine();
        setResponse(request.post(url)
                .then()
                .extract()
                .response());
        request.delete();
        logger.info(getResponse());
        return getResponse();
    }

    @Override
    public Response post() throws Exception{
        checkReqDefine();
        setResponse(request.post()
                .then()
                .extract()
                .response());
        request.delete();
        logger.info(getResponse());
        return getResponse();
    }

    @Override
    public Response get(String url) throws Exception{
        checkReqDefine();
        setResponse(request.get(url)
                .then()
                .extract()
                .response());
        request.delete(url);
        logger.info(getResponse());
        return getResponse();
    }

    @Override
    public Response get() throws Exception{
        checkReqDefine();
        setResponse(request.get()
                .then()
                .extract()
                .response());
        request.delete();
        return getResponse();
    }

    @Override
    public XmlPath getXmlPath() throws Exception{
        checkReqDefine();
        return getResponse().xmlPath();
    }

    @Override
    public void addHeaders(Map<String, String> headers) throws Exception{
        checkReqDefine();
        request.headers(headers);
        logger.info("{} headers added", headers);
    }


    @Override
    public void addHeader(String key, String value) throws Exception{
        checkReqDefine();
        request.header(key,value);
        logger.info("{}, {} header added", key, value);
    }

    private void checkRespNull() throws Exception {
        if (response == null) {
            logger.error("Can't find response");
            throw new Exception();
        }
    }

    @Override
    public Map<String, String> addQueryParams(Map<String, String> parameters) throws Exception{
        checkReqDefine();
        RequestSpecification requestSpecification = request.formParams(parameters);
        queryRequest = SpecificationQuerier.query(requestSpecification);
        Map<String, String> map = queryRequest.getQueryParams();
        logger.info("{} form params added", parameters);
        return map;
    }

    @Override
    public String addQueryParam(String queryParamKey, String queryParamValue) throws Exception{
        checkReqDefine();
        RequestSpecification requestSpecification = request.formParam(queryParamKey,queryParamValue);
        queryRequest = SpecificationQuerier.query(requestSpecification);
        String queryParam = queryRequest.getQueryParams().toString();
        logger.info("{}, {} query param added",queryParamKey,queryParamValue);
        return queryParam;
    }

    @Override
    public String addPathParam(String pathParamKey, String pathParamValue) throws Exception{
        checkReqDefine();
        RequestSpecification requestSpecification = request.pathParam(pathParamKey,pathParamValue);
        queryRequest = SpecificationQuerier.query(requestSpecification);
        String pathParam = queryRequest.getPathParams().toString();
        logger.info("{}, {} path param added",pathParamKey,pathParamValue);
        return pathParam;
    }

    @Override
    public Map<String, String> addFormParams(Map<String, String> formParams) throws Exception{
        checkReqDefine();
        RequestSpecification requestSpecification = request.formParams(formParams);
        queryRequest = SpecificationQuerier.query(requestSpecification);
        Map<String, String> map = queryRequest.getFormParams();
        logger.info("{} form params added", formParams);
        return map;
    }

    @Override
    public String addFormParam(String paramKey, String paramValue) throws Exception{
        checkReqDefine();
        RequestSpecification requestSpecification = request.formParam(paramKey,paramValue);
        queryRequest = SpecificationQuerier.query(requestSpecification);
        String formParam = queryRequest.getFormParams().toString();
        logger.info("{}, {} form param added",paramKey,paramValue);
        return formParam;
    }

    @Override
    public String addPayload(Object payload) throws Exception {
        checkReqDefine();
        RequestSpecification requestSpecification = request.body(payload);
        queryRequest = SpecificationQuerier.query(requestSpecification);
        String requestBody = queryRequest.getBody();
        logger.info("{} request body added", payload);
        return requestBody;
    }

    @Override
    public String addFullUrl(String url, String basePath) throws Exception {
        checkReqDefine();
        RequestSpecification requestSpecification = request.baseUri(url).basePath(basePath);
        queryRequest = SpecificationQuerier.query(requestSpecification);
        String retrieveURI = queryRequest.getBaseUri();
        String retrievePath = queryRequest.getBasePath();
        logger.info("{}{} is request full url", url, basePath);
        return retrieveURI + retrievePath;
    }

    @Override
    public String addBaseUrl(String url) throws Exception {
        checkReqDefine();
        RequestSpecification requestSpecification = request.baseUri(url);
        queryRequest = SpecificationQuerier.query(requestSpecification);
        String retrieveURI = queryRequest.getBaseUri();
        logger.info("'{}' base url", url);
        return retrieveURI;
    }

    @Override
    public String addBasePath(String url) throws Exception {
        checkReqDefine();
        RequestSpecification requestSpecification = request.basePath(url);
        queryRequest = SpecificationQuerier.query(requestSpecification);
        String retrievePath = queryRequest.getBasePath();
        logger.info("'{}' endpoint", url);
        return retrievePath;
    }

    @Override
    public JsonPath getJsonPath() throws Exception {
        checkReqDefine();
        logger.info("response value jsonpath");
        return getResponse().jsonPath();
    }

    @Override
    public void checkStatusCode(int statusCode) throws Exception{
        logger.info("Status code value is {} control", statusCode);
        checkReqDefine();
        try {
            assert statusCode == getResponse().statusCode();
        } catch (AssertionError e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public String getXmlValue(String tagName) throws Exception{
        checkRespNull();
        return getResponse().getBody().xmlPath().get(tagName);
    }

    @Override
    public String getResponseBody() throws Exception{
        checkRespNull();
        return getResponse().getBody().prettyPrint();
    }

    @Override
    public Long getResponseTime() throws Exception{
        checkReqDefine();
        return getResponse().getTime();
    }

    @Override
    public Object getJsonValue(String parameterKey) throws Exception{
        if (getResponse() != null){
            return getResponse().jsonPath().get(parameterKey);
        } else {
            throw new Exception();
        }
    }

    public void setRequest(RequestSpecification request){this.request = request;}
    public Response getResponse(){return response;}
    public void setResponse(Response response){this.response = response;}
}
