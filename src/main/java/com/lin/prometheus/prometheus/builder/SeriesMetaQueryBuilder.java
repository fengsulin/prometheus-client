package com.lin.prometheus.prometheus.builder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class SeriesMetaQueryBuilder implements QueryBuilder{
    private static final String TARGET_URI = "/api/v1/series?";
    private static final String TARGET_URI_PATTERN_SUFFIX = "#{selector}&start=#{start}&end=#{end}";

    private static final String START_EPOCH_TIME = "start";
    private static final String END_EPOCH_TIME = "end";
    private static final String QUERY_STRING = "selector";

    private String serverUri;
    private Map<String,Object> params = new HashMap<>();

    public SeriesMetaQueryBuilder(String serverUri){
        this.serverUri = serverUri + TARGET_URI;
    }

    public SeriesMetaQueryBuilder withSelector(String selector){
        try {
            params.put(QUERY_STRING, URLEncoder.encode(selector,"utf-8")
                    .replaceAll("%3D","=")
                    .replaceAll("%26","&"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this;
    }

    public SeriesMetaQueryBuilder withStartEpochTime(long startTime){
        params.put(START_EPOCH_TIME,startTime);
        return this;
    }

    public SeriesMetaQueryBuilder withEndEpochTime(long endTime){
        params.put(END_EPOCH_TIME,endTime);
        return this;
    }

    public URI build(){
        return URI.create(Utils.namedFormat(serverUri + Utils.namedFormat(TARGET_URI_PATTERN_SUFFIX,params),params));
    }

    private boolean validate(){
        return true;
    }
}
