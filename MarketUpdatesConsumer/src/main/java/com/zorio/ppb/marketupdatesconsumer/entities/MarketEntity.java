package com.zorio.ppb.marketupdatesconsumer.entities;

import com.zorio.ppb.marketupdatesconsumer.entities.primary_keys.MarketPK;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("markets")
public class MarketEntity {

    @PrimaryKey
    private MarketPK primaryKey;
    private String name;

    public MarketEntity(MarketPK primaryKey, String name) {
        this.primaryKey = primaryKey;
        this.name = name;
    }

    public MarketPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(MarketPK primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
