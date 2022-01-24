package org.example.api;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.util.Map;

/**
 * @author Semih Saydam
 * @date 24.01.2022
 */
interface IRest {

    /*
     * Create request object
     */
    public void createRequest();

    /*
     * post request
     */
    Response post(String url) throws Exception;
    Response post() throws Exception;

    /*
     * get request
     */
    Response get(String url) throws Exception;
    Response get() throws Exception;

    /*
     * add headers for request
     */
    public void addHeaders(Map<String, String> headers) throws Exception;

    /*
     * add header for request
     */
    public void addHeader(String key, String value) throws Exception;

    /*
     * add query params for request
     */
    Map<String, String> addQueryParams(Map<String, String> parameters) throws Exception;

    /*
     * add query params for request
     */
    String addQueryParam(String queryParamKey, String queryParamValue) throws Exception;

    /*
     * add query params for request
     */
    String addPathParam(String pathParamKey, String pathParamValue) throws Exception;

    /*
     * add form params for request
     */
    Map<String, String> addFormParams(Map<String, String> formParams) throws Exception;

    /*
     * add formParam params for request
     */
    String addFormParam(String formParamKey, String formParamValue) throws Exception;

    /*
     * add request body for request
     */
    String addPayload(Object payload) throws Exception;

    /*
     * get restAssured xmlpath
     */

    XmlPath getXmlPath() throws Exception;

    /*
     * add request base url for request
     */
    String addBaseUrl(String url) throws Exception;

    /*
     * add request full url for request
     */
    String addFullUrl(String baseUrl, String basePath) throws Exception;


    /*
     * add request base path for request
     */
    String addBasePath(String url) throws Exception;

    /*
     * response HTTP status code control
     */
    public void checkStatusCode(int statusCode) throws Exception;

    /*
     * for response time controls
     */
    Long getResponseTime() throws Exception;

    /*
     * get Xml value with hierarchy
     */
    String getXmlValue(String tagName) throws Exception;


    /*
     * get Json value with hierarchy
     */
    Object getJsonValue(String parameterKey) throws Exception;
    JsonPath getJsonPath() throws Exception;
    String getResponseBody() throws Exception;
}