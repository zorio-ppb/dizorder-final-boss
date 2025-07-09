package com.zorio.ppb.marketupdatesconsumer.repositories;

import com.zorio.ppb.marketupdatesconsumer.entities.MarketEntity;
import com.zorio.ppb.marketupdatesconsumer.entities.primary_keys.MarketPK;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MarketRepository extends CrudRepository<MarketEntity, MarketPK> {

    @Query("UPDATE markets SET name = ?2 WHERE market_id = ?0 AND event_id = ?1")
    public void updateMarketName(String marketID, String eventID, String name);

}
