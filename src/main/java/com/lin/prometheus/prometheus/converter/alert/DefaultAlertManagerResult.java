package com.lin.prometheus.prometheus.converter.alert;

import com.lin.prometheus.prometheus.converter.Result;

import java.util.ArrayList;
import java.util.List;

public class DefaultAlertManagerResult extends Result<AlertManagerResultItem> {
    List<AlertManagerResultItem> activeAlertManagers = new ArrayList<AlertManagerResultItem>();
    List<AlertManagerResultItem> droppedAlertManagers = new ArrayList<AlertManagerResultItem>();
    public void addActiveManager(AlertManagerResultItem data) {
        activeAlertManagers.add(data);
    }

    public void addDroppedManager(AlertManagerResultItem data) {
        droppedAlertManagers.add(data);
    }

    @Override
    public List<AlertManagerResultItem> getResult() {
        return activeAlertManagers;
    }

    @Override
    public String toString() {
        return "TargetResultItem [activeAM=" + activeAlertManagers + ",droppedAM="+ droppedAlertManagers +"]";
    }

}