package com.zorio.ppb.marketupdatesproducer;

import com.zorio.ppb.marketupdatescore.MarketUpdate;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Autowired
    Environment env;

    Map<String, Object> config() {

        Map<String, Object> properties = new HashMap<String, Object>();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("spring.kafka.producer.bootstrap-servers"));
        properties.put(ProducerConfig.ACKS_CONFIG, env.getProperty("spring.kafka.producer.acks"));
        properties.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, env.getProperty("spring.kafka.producer.properties.delivery.timeout.ms"));
        properties.put(ProducerConfig.LINGER_MS_CONFIG, env.getProperty("spring.kafka.producer.properties.linger.ms"));
        properties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, env.getProperty("spring.kafka.producer.properties.request.timeout.ms"));
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, env.getProperty("spring.kafka.producer.properties.enable.idempotence"));
        properties.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, env.getProperty("spring.kafka.producer.properties.max.in.flight.requests.per.connection"));
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return properties;

    }

    @Bean
    public ProducerFactory<String, MarketUpdate> producerFactory() { return new DefaultKafkaProducerFactory<>(config()); }

    @Bean
    public KafkaTemplate<String, MarketUpdate> kafkaTemplate() { return new KafkaTemplate<>(producerFactory()); }

    @Bean
    NewTopic createTopic() {
        return TopicBuilder.name("market-update-events-topic")
                .partitions(3)
                .replicas(3)
                .config("min.insync.replicas", "2")
                .build();
    }

}
