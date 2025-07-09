package com.zorio.ppb.marketupdatesconsumer.entities.primary_keys;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Objects;

@PrimaryKeyClass
public class MarketPK implements Serializable {

    @PrimaryKeyColumn(name = "market_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String marketID;

    @PrimaryKeyColumn(name = "event_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private String eventID;

    public MarketPK(String marketID, String eventID) {
        this.marketID = marketID;
        this.eventID = eventID;
    }

    public String getMarketID() {
        return marketID;
    }

    public void setMarketID(String marketID) {
        this.marketID = marketID;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarketPK marketPK = (MarketPK) o;
        return Objects.equals(marketID, marketPK.marketID) &&
                Objects.equals(eventID, marketPK.eventID);
    }

    @Override
    public int hashCode() { return Objects.hash(marketID, eventID); }

}
