package com.zorio.ppb.marketupdatesconsumer.entities.primary_keys;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.util.Objects;

@PrimaryKeyClass
public class SelectionPK {

    @PrimaryKeyColumn(name = "selection_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String selectionID;

    @PrimaryKeyColumn(name = "market_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private String marketID;

    public SelectionPK(String selectionID, String marketID) {
        this.selectionID = selectionID;
        this.marketID = marketID;
    }

    public String getSelectionID() {
        return selectionID;
    }

    public void setSelectionID(String selectionID) {
        this.selectionID = selectionID;
    }

    public String getMarketID() {
        return marketID;
    }

    public void setMarketID(String marketID) {
        this.marketID = marketID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectionPK that = (SelectionPK) o;
        return Objects.equals(selectionID, that.selectionID) && Objects.equals(marketID, that.marketID);
    }

    @Override
    public int hashCode() { return Objects.hash(selectionID, marketID); }

}
