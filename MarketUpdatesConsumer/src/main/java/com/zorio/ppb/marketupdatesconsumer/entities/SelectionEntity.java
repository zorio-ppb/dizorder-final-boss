package com.zorio.ppb.marketupdatesconsumer.entities;

import com.zorio.ppb.marketupdatesconsumer.entities.primary_keys.SelectionPK;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("selections")
public class SelectionEntity {

    @PrimaryKey
    private SelectionPK primaryKey;
    private String name;
    private Float odd;

    public SelectionEntity(SelectionPK primaryKey, String name, Float odd) {
        this.primaryKey = primaryKey;
        this.name = name;
        this.odd = odd;
    }

    public SelectionPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(SelectionPK primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getOdd() {
        return odd;
    }

    public void setOdd(Float odd) {
        this.odd = odd;
    }

}
