package com.lin.prometheus.prometheus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrometheusUriProperties {
    @Value("${prometheus.uri}")
    private String uri;

    public String getUri() {
        return uri;
    }
}
