package com.zorio.ppb.marketupdatesconsumer.entities;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("events")
public class EventEntity {

    @PrimaryKey
    private String id;
    private String name;
    private String date;

    public EventEntity(String id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


}
