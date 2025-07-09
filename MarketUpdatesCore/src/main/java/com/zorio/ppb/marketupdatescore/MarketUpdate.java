package com.zorio.ppb.marketupdatescore;

public class MarketUpdate {

    private String id;
    private String name;
    private String operation;
    private YellowEvent event;
    private Selection[] selections;

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
