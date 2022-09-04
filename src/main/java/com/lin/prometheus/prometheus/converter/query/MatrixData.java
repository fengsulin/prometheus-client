package com.lin.prometheus.prometheus.converter.query;

import com.lin.prometheus.prometheus.converter.Data;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MatrixData implements Data {

    private Map<String,String> metric = new HashMap<>();
    private QueryResultItemValue[] dataValues;

    public Map<String, String> getMetric() {
        return metric;
    }

    public void setMetric(Map<String, String> metric) {
        this.metric = metric;
    }

    public QueryResultItemValue[] getDataValues() {
        return dataValues;
    }

    public void setDataValues(QueryResultItemValue[] dataValues) {
        this.dataValues = dataValues;
    }

    public double[] getValues(){
        double[] values = new double[dataValues.length];
        int index = 0;
        for (QueryResultItemValue dataValue : dataValues){
            values[index++] = dataValue.getValue();
        }
        return values;
    }

    public double[] getTimestamps(){
        double[] timestamps = new double[dataValues.length];
        int index = 0;
        for (QueryResultItemValue dataValue : dataValues){
            timestamps[index++] = dataValue.getTimestamp();
        }
        return timestamps;
    }

    public String[] getFormattedTimestamps(String format){
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String[] timestamps = new String[dataValues.length];
        int index = 0;
        for (QueryResultItemValue dataValue : dataValues){
            timestamps[index++] = formatter.format(new Date(Math.round(dataValue.getTimestamp()*1000L)));
        }
        return timestamps;
    }

    @Override
    public String toString() {
        return "MatrixData [metric=" + metric + ", dataValues=" + Arrays.toString(dataValues) + "]";
    }
}
