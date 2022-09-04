package com.lin.prometheus.prometheus.converter.query;

import com.lin.prometheus.prometheus.converter.Data;
import com.lin.prometheus.prometheus.converter.Result;

import java.util.ArrayList;
import java.util.List;

public class DefaultQueryResult<T extends Data> extends Result<T> {

    List<T> result = new ArrayList<>();

    public void addData(T data){
        result.add(data);
    }

    @Override
    public List<T> getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "DefaultQueryResult [result=" + result +"]";
    }
}
