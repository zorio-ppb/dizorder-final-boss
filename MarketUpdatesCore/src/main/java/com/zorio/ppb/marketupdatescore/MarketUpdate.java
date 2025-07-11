package com.zorio.ppb.marketupdatescore;

import jakarta.validation.constraints.NotNull;

public class MarketUpdate {

    @NotNull
    private String id;

    @NotNull
    private String operation;

    @NotNull
    private YellowEvent event;

    @NotNull
    private Selection[] selections;

    private String name;

    public MarketUpdate() {}
    public MarketUpdate(String id, String name, String operation, YellowEvent event, Selection[] selections) {
        this.id = id;
        this.name = name;
        this.operation = operation;
        this.event = event;
        this.selections = selections;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public YellowEvent getEvent() {
        return event;
    }

    public void setEvent(YellowEvent event) {
        this.event = event;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Selection[] getSelections() {
        return selections;
    }

    public void setSelections(Selection[] selections) {
        this.selections = selections;
    }


}
