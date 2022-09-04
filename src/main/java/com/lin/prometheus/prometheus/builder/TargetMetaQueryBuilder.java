package com.lin.prometheus.prometheus.builder;

import java.net.URI;

public class TargetMetaQueryBuilder implements QueryBuilder{
    private static final String TARGET_URI_PATTERN_SUFFIX = "/api/v1/targets";

    private String targetUriPattern;

    public TargetMetaQueryBuilder(String serverUri){
        this.targetUriPattern = serverUri + TARGET_URI_PATTERN_SUFFIX;
    }

    public URI build(){
        return URI.create(targetUriPattern);
    }

    private boolean validate(){
        return true;
    }
}
