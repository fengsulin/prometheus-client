package com.lin.prometheus.prometheus.converter.query;

import com.lin.prometheus.prometheus.converter.Data;

public class ScalarData extends QueryResultItemValue implements Data {
    public ScalarData(double timestamp, double value) {
        super(timestamp, value);
    }
}
