package com.lin.prometheus.prometheus.builder;

import java.net.URI;

public class AlertManagerMetaQueryBuilder implements QueryBuilder{
    private static final String TARGET_URI_PATTERN_SUFFIX = "/api/v1/alertmanagers";

    private String targetUriPattern;

    public AlertManagerMetaQueryBuilder(String serverUri){
        this.targetUriPattern = serverUri + TARGET_URI_PATTERN_SUFFIX;
    }

    public URI build(){
        return URI.create(targetUriPattern);
    }

    private boolean validate(){
        return true;
    }
}
