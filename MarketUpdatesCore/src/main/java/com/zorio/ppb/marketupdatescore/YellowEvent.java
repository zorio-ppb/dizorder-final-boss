package com.zorio.ppb.marketupdatescore;

import java.util.Arrays;

public class YellowEvent {

    private String id;
    private String name;
    private String date;
    private Market[] markets;

    public YellowEvent() {}
    public YellowEvent(String id, String name, String date, Market[] markets) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.markets = markets;
    }

    public Market[] getMarkets() { return markets; }
    public void setMarkets(Market[] markets) { this.markets = markets; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() { return String.format("YellowEvent [id=%s, name=%s, date=%s]\nmarkets: %s", id, name, date, Arrays.toString(markets)); }


}
