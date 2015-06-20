package com.oxf1.spider.request.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxf1.spider.request.HttpRequestMethod;
import com.oxf1.spider.request.Request;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Created by cxu on 2014/11/21.
 */
public class HttpRequest implements Request{

    private String url;//请求的url
    private HttpRequestMethod httpMethod;//请求的http方法，GET|POST等
    private Map<String, String> formParameters;//如果是post请求，这里存放请求参数

    /**
     * 从队列里的json字符串来创建一个HttpRequest
     * @param jsonString
     * @return
     */
    public static HttpRequest build(String jsonString) throws IOException {
        if(StringUtils.isNotBlank(jsonString)) {
            ObjectMapper mapper = new ObjectMapper();
            HttpRequest req =  mapper.readValue(jsonString, HttpRequest.class);
            return req;
        }

        return null;
    }

    /**
     * 构造函数
     * @param url
     * @param httpMethod
     * @param formParameters
     */
    private HttpRequest(String url, HttpRequestMethod httpMethod, Map<String, String> formParameters) {
        this.url = url;
        this.httpMethod = httpMethod;
        this.formParameters = formParameters;
    }

    private HttpRequest(){}

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public HttpRequestMethod getHttpMethod() {
        return this.httpMethod;
    }

    @Override
    public Map<String, String> getFormParameters() {
        return null;
    }

    @JsonProperty("url")
    public void setUrl(String url){
        this.url = url;
    }

    @JsonProperty("http_method")
    public void setHttpMethod(String method)
    {
        this.httpMethod = HttpRequestMethod.valueOf(method);
    }

    @JsonProperty("post_parms")
    public void SetFormParameters(Map<String,String> params){
        this.formParameters = params;
    }
}