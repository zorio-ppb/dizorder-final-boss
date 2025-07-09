package com.zorio.ppb.marketupdatesconsumer.handler;

import com.zorio.ppb.marketupdatesconsumer.entities.EventEntity;
import com.zorio.ppb.marketupdatesconsumer.entities.MarketEntity;
import com.zorio.ppb.marketupdatesconsumer.entities.SelectionEntity;
import com.zorio.ppb.marketupdatesconsumer.entities.primary_keys.MarketPK;
import com.zorio.ppb.marketupdatesconsumer.entities.primary_keys.SelectionPK;
import com.zorio.ppb.marketupdatesconsumer.repositories.EventRepository;
import com.zorio.ppb.marketupdatesconsumer.repositories.MarketRepository;
import com.zorio.ppb.marketupdatesconsumer.repositories.SelectionRepository;
import com.zorio.ppb.marketupdatescore.Market;
import com.zorio.ppb.marketupdatescore.MarketUpdate;
import com.zorio.ppb.marketupdatescore.Selection;
import com.zorio.ppb.marketupdatescore.YellowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@KafkaListener(topics = "market-update-events-topic")
public class MarketUpdateEventHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CassandraOperations cassandraOperations;

    private final SelectionRepository selectionRepository;
    private final MarketRepository marketRepository;
    private final EventRepository eventRepository;

    public MarketUpdateEventHandler(SelectionRepository selectionRepository, MarketRepository marketRepository, EventRepository eventRepository) {
        this.selectionRepository = selectionRepository;
        this.marketRepository = marketRepository;
        this.eventRepository = eventRepository;
    }

    @KafkaHandler
    public void handle(MarketUpdate marketUpdate) {

        logger.info("Received market update: {} to {}", marketUpdate.getOperation(), marketUpdate.getName());

        Market market = new Market(
                marketUpdate.getId(),
                marketUpdate.getName(),
                marketUpdate.getSelections()
        );

        YellowEvent event = new YellowEvent(
                marketUpdate.getEvent().getId(),
                marketUpdate.getEvent().getName(),
                marketUpdate.getEvent().getDate(),
                new Market[] { market }
        );

        update(market, event, marketUpdate.getOperation());

    }

    public void update(Market market, YellowEvent event, String operation) {

        EventEntity newEvent = new EventEntity( event.getId(), event.getName(), event.getDate() );
        MarketEntity marketEntity = new MarketEntity( new MarketPK(market.getId(), event.getId()), market.getName() );

        List<SelectionEntity> selectionEntities = new ArrayList<>();
        for (Selection selection : market.getSelections()) {

            selectionEntities.add(new SelectionEntity(
                    new SelectionPK(selection.getId(), market.getId()),
                    selection.getName(),
                    selection.getOdd()
            ));

        }

        switch (operation) {
            case "ADD" -> add(newEvent, marketEntity, selectionEntities);
            case "MODIFY" -> modify(newEvent, marketEntity, selectionEntities);
            case "DELETE" -> delete(newEvent, marketEntity, selectionEntities);
            default -> logger.warn("Unknown operation: {}", operation);
        }

    }

    private void add(EventEntity newEvent, MarketEntity marketEntity, List<SelectionEntity> selectionEntities) {

        // under no circumstances should there be a scenario where an event doesn't exist, but a market for the new event already exists
        if (!eventRepository.existsById(newEvent.getId())) {
            eventRepository.save(newEvent);
            logger.info("New event added: [{}]", newEvent.getName());
        }

        if (marketRepository.existsById(marketEntity.getPrimaryKey())) {
            logger.info("[{}] event already up to date", newEvent.getName());
            return;
        }

        marketRepository.save(marketEntity);
        selectionRepository.saveAll(selectionEntities);
        logger.info("Added market [{}] to [{}] event", marketEntity.getName(), newEvent.getName());

    }

    private void modify(EventEntity eventEntity, MarketEntity newMarket, List<SelectionEntity> selectionEntities) {

        if (!eventRepository.existsById(eventEntity.getId())) {
            logger.warn("No such event to modify: [{}]", eventEntity.getName());
            return;
        }

        if (!marketRepository.existsById(newMarket.getPrimaryKey())) {
            logger.warn("No market [{}] to modify within event [{}]", newMarket.getName(), eventEntity.getName());
            return;
        }

        marketRepository.updateMarketName(
                newMarket.getPrimaryKey().getMarketID(),
                newMarket.getPrimaryKey().getEventID(),
                newMarket.getName()
        );

        selectionEntities.forEach(selection -> { cassandraOperations.update( selection ); });

        logger.info("Updated market [{}] on [{}] event", newMarket.getName(), eventEntity.getName());

    }

    private void delete(EventEntity eventEntity, MarketEntity deletedMarket, List<SelectionEntity> selectionEntities) {

        if (!eventRepository.existsById(eventEntity.getId())) {
            logger.warn("No such event to delete market from: [{}]", eventEntity.getName());
            return;
        }

        if (!marketRepository.existsById(deletedMarket.getPrimaryKey())) {
            logger.warn("No market [{}] to delete within event [{}]", deletedMarket.getName(), eventEntity.getName());
            return;
        }

        selectionEntities.forEach(selection -> {
            cassandraOperations.delete(
                    Query.query( Criteria.where("market_id").is( selection.getPrimaryKey().getMarketID()) )
                            .and( Criteria.where("selection_id").is(selection.getPrimaryKey().getSelectionID()) ),
                    SelectionEntity.class
            );
        });

        marketRepository.delete(deletedMarket);
        logger.info("Deleted market [{}] from [{}] event", deletedMarket.getName(), eventEntity.getName());

    }

}
