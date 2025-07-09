package com.zorio.ppb.marketupdatesproducer.service;

import com.zorio.ppb.marketupdatescore.MarketUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class MarketUpdateServiceImpl implements MarketUpdateService {

    private final KafkaTemplate<String, MarketUpdate> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public MarketUpdateServiceImpl(KafkaTemplate<String, MarketUpdate> kafkaTemplate) { this.kafkaTemplate = kafkaTemplate; }

    @Override
    public String updateMarket(MarketUpdate marketUpdate) throws ExecutionException, InterruptedException {

        LOGGER.info("Publishing new market update...");

        SendResult<String, MarketUpdate> result = kafkaTemplate.send(
                "market-update-events-topic",
                marketUpdate.getId(),
                marketUpdate
        ).get();

        LOGGER.info("******* New market update ({} with ID {})", marketUpdate.getOperation(), result.getProducerRecord().key());

        return "Success";

    }

}
