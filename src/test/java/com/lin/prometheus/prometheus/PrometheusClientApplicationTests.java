package com.lin.prometheus.prometheus;

import com.lin.prometheus.prometheus.builder.*;
import com.lin.prometheus.prometheus.config.PrometheusUriProperties;
import com.lin.prometheus.prometheus.converter.ConvertUtils;
import com.lin.prometheus.prometheus.converter.Data;
import com.lin.prometheus.prometheus.converter.alert.AlertManagerResultItem;
import com.lin.prometheus.prometheus.converter.alert.DefaultAlertManagerResult;
import com.lin.prometheus.prometheus.converter.label.DefaultLabelResult;
import com.lin.prometheus.prometheus.converter.query.DefaultQueryResult;
import com.lin.prometheus.prometheus.converter.query.MatrixData;
import com.lin.prometheus.prometheus.converter.query.VectorData;
import com.lin.prometheus.prometheus.converter.series.DefaultSeriesResult;
import com.lin.prometheus.prometheus.converter.series.SeriesResultItem;
import com.lin.prometheus.prometheus.converter.status.DefaultConfigResult;
import com.lin.prometheus.prometheus.converter.target.DefaultTargetResult;
import com.lin.prometheus.prometheus.converter.target.TargetResultItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

@SpringBootTest
class PrometheusClientApplicationTests {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PrometheusUriProperties prometheusUriProperties;

    @Test
    void contextLoads() {
        System.out.println(System.currentTimeMillis() / 1000 - 60*10);
        System.out.println(System.currentTimeMillis() / 1000);
    }

    @Test
    void queryTest() throws MalformedURLException {
        InstantQueryBuilder queryBuilder = QueryBuilderType.InstantQuery.newInstance(prometheusUriProperties.getUri());
        URI targetUri = queryBuilder.withQuery("up")
                .withEpochTime(System.currentTimeMillis() / 1000)
                .withTimeout("10s")
                .build();
        System.out.println(targetUri.toURL().toString());
        String jsonString = restTemplate.getForObject(targetUri, String.class);
        System.out.println(jsonString);
        DefaultQueryResult<VectorData> dataDefaultQueryResult = ConvertUtils.convertQueryResultString(jsonString);
        List<VectorData> result = dataDefaultQueryResult.getResult();
        String resultType = dataDefaultQueryResult.getResultType();
        String status = dataDefaultQueryResult.getStatus();
        System.out.println(resultType);
        System.out.println(status);
        System.out.println(result);
        result.stream().forEach(res -> {
            System.out.println(res.getFormattedTimestamp("yyyy-MM-dd HH:mm:ss"));
            System.out.println(res.getMetric().toString());
            System.out.println(res.getDataValue().toString());
            System.out.println(res.getValue());
        });
    }

    @Test
    void rangeQueryTest(){
        RangeQueryBuilder rangeQueryBuilder = QueryBuilderType.RangeQuery.newInstance(prometheusUriProperties.getUri());
        URI rangeUri = rangeQueryBuilder.withQuery("up")
                .withStartEpochTime(System.currentTimeMillis() / 1000 - 120)
                .withEndEpochTime(System.currentTimeMillis() / 1000)
                .withStep("30s")
                .build();
        String jsonString = restTemplate.getForObject(rangeUri, String.class);
        System.out.println(jsonString);
        DefaultQueryResult<MatrixData> dataDefaultQueryResult = ConvertUtils.convertQueryResultString(jsonString);
        System.out.println(dataDefaultQueryResult.getStatus());
        System.out.println(dataDefaultQueryResult.getResultType());
        System.out.println(dataDefaultQueryResult.getResult().toString());
    }

    @Test
    void targetQueryTest(){
        TargetMetaQueryBuilder targetQueryBuilder = QueryBuilderType.TargetMetaQuery.newInstance(prometheusUriProperties.getUri());
        URI targetUri = targetQueryBuilder.build();
        String jsonString = restTemplate.getForObject(targetUri, String.class);
        System.out.println(jsonString);
        DefaultTargetResult defaultTargetResult = ConvertUtils.convertTargetResultString(jsonString);
        List<TargetResultItem> result = defaultTargetResult.getResult();
        System.out.println(result.toString());
    }

    @Test
    void seriesQueryTest(){
        SeriesMetaQueryBuilder seriesQueryBuilder = QueryBuilderType.SeriesMetaQuery.newInstance(prometheusUriProperties.getUri());
        URI seriesUri = seriesQueryBuilder.withSelector("match[]=up")
                .withStartEpochTime(System.currentTimeMillis() / 1000 - 120)
                .withEndEpochTime(System.currentTimeMillis() / 1000)
                .build();
        String jsonString = restTemplate.getForObject(seriesUri, String.class);
        DefaultSeriesResult defaultSeriesResult = ConvertUtils.convertSeriesResultString(jsonString);
        List<SeriesResultItem> result = defaultSeriesResult.getResult();
        System.out.println(result.toString());
    }

    @Test
    void configQueryTest(){
        StatusMetaQueryBuilder configQueryBuilder = QueryBuilderType.StatusMetaQuery.newInstance(prometheusUriProperties.getUri());
        URI configUri = configQueryBuilder.build();
        String jsonString = restTemplate.getForObject(configUri, String.class);
        DefaultConfigResult defaultConfigResult = ConvertUtils.convertConfigResultString(jsonString);
        List<String> result = defaultConfigResult.getResult();
        System.out.println(result);
    }

    @Test
    void alertManagerQueryTest(){
        AlertManagerMetaQueryBuilder alertManagerQueryBuilder = QueryBuilderType.AlertManagerMetaQuery.newInstance(prometheusUriProperties.getUri());
        URI alertManagerUri = alertManagerQueryBuilder.build();
        String jsonString = restTemplate.getForObject(alertManagerUri, String.class);
        System.out.println(jsonString);
        DefaultAlertManagerResult defaultAlertManagerResult = ConvertUtils.convertAlertManagerResultString(jsonString);
        List<AlertManagerResultItem> result = defaultAlertManagerResult.getResult();
        System.out.println(result.toString());
    }

    @Test
    void labelQueryTest(){
        LabelMetaQueryBuilder labelQueryBuilder = QueryBuilderType.LabelMetaQuery.newInstance(prometheusUriProperties.getUri());
        URI labelUri = labelQueryBuilder.withLabel("instance")
                .build();
        String jsonString = restTemplate.getForObject(labelUri, String.class);
        System.out.println(jsonString);
        DefaultLabelResult defaultLabelResult = ConvertUtils.convertLabelResultString(jsonString);
        List<String> result = defaultLabelResult.getResult();
        System.out.println(result);
    }
}
