package com.lin.prometheus.prometheus.converter.label;

import com.lin.prometheus.prometheus.converter.Result;

import java.util.ArrayList;
import java.util.List;

public class DefaultLabelResult extends Result<String> {
    List<String> result = new ArrayList<String>();
    public void addData(String data) {
        result.add(data);
    }

    @Override
    public List<String> getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "DefaultLabelResult [result=" + result + "]";
    }

}
