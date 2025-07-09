package com.zorio.ppb.marketupdatescore;

import java.util.Arrays;

public class Market {

    private String id;
    private String name;
    private Selection[] selections;

    public Market() {}
    public Market(String id, String name, Selection[] selections) {
        this.id = id;
        this.name = name;
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

    public Selection[] getSelections() {
        return selections;
    }

    public void setSelections(Selection[] selections) {
        this.selections = selections;
    }

    @Override
    public String toString() { return String.format("Market [id=%s, name=%s, selections=%s]", id, name, Arrays.toString(selections)); }


}
