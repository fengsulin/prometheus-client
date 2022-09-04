package com.lin.prometheus.prometheus.builder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class InstantQueryBuilder implements QueryBuilder{
    private static final String TARGET_URI_PATTERN_SUFFIX = "/api/v1/query?query=#{query}&time=#{time}&timeout=#{timeout}";

    private static final String EPOCH_TIME = "time";
    private static final String TIMEOUT = "timeout";
    private static final String QUERY_STRING = "query";

    private String targetUriPattern;
    private Map<String,Object> params = new HashMap<>();

    /**
     * 构造函数：设置默认的http请求api地址，timeout，time
     * @param serverUri：prometheus的服务地址
     */
    public InstantQueryBuilder(String serverUri){
        targetUriPattern = serverUri + TARGET_URI_PATTERN_SUFFIX;
        params.put(TIMEOUT,"");
        params.put(EPOCH_TIME,"");
    }

    /**
     * 设置查询promSql
     * @param query：promSql
     * @return
     */
    public InstantQueryBuilder withQuery(String query){
        try {
            params.put(QUERY_STRING, URLEncoder.encode(query,"utf-8")); // 防止Sql传输过程中改变
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this;
    }

    public InstantQueryBuilder withEpochTime(long time){
        params.put(EPOCH_TIME,time);
        return this;
    }

    public InstantQueryBuilder withTimeout(String timeout){
        params.put(TIMEOUT,timeout);
        return this;
    }

    public URI build(){
        return URI.create(Utils.namedFormat(targetUriPattern,params));
    }

    private boolean validate(){
        return true;
    }
}
