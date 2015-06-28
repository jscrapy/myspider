package com.oxf1.spider.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;

/**
 * 代表了一个Queue里的url请求
 * Created by cxu on 2014/11/21.
 */
public abstract class Request {

    /**
     * 从队列里的json字符串来创建一个HttpRequest
     * @param jsonString
     * @return
     */
    public static Request build(String jsonString, Class clazz) throws IOException {
        if(StringUtils.isNotBlank(jsonString)) {
            ObjectMapper mapper = new ObjectMapper();
            Request req =  (Request)mapper.readValue(jsonString, clazz);
            return req;
        }

        return null;
    }

    /**
     * 请求地址
     * @return
     */
    public abstract String getUrl();

    /**
     * 获取url的请求方法：GET|POST|DELETE|TRACE|HEAD..
     * @return
     */
    public abstract HttpRequestMethod getHttpMethod();

    /**
     * 如果是POST请求，获取请求的参数
     * @return
     */
    public abstract Map<String, String> getParameters();

    public abstract String asJson();

    /**
     * 对象的一个md5标识，用于去重
     * @return
     */
    public abstract String fp();

}
