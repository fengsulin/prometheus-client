package com.lin.prometheus.prometheus.builder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class LabelMetaQueryBuilder implements QueryBuilder{
    private static final String TARGET_URI_PATTERN_SUFFIX = "/api/v1/label/#{label}/values";

    private static final String LABEL_STRING = "label";

    private String targetUriPattern;
    private Map<String,Object> params = new HashMap<>();

    public LabelMetaQueryBuilder(String serverUri){
        this.targetUriPattern = serverUri + TARGET_URI_PATTERN_SUFFIX;
    }

    public LabelMetaQueryBuilder withLabel(String label){
        params.put(LABEL_STRING,label);
        return this;
    }

    public URI  build(){
        return URI.create(Utils.namedFormat(targetUriPattern,params));
    }

    private boolean validate(){
        return true;
    }
}
