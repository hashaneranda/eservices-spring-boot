package com.hashan.nagp.consumerservice.configurations;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for kafka producer configurations.
 */
@Configuration
public class KafkaProducerConfiguration {
    @Value("${kafka.config.bootstrapAddress}")
    private String bootstrapAddress;

    @Value("${kafka.order-notifications.topic}")
    private String orderNotificationsTopic;

    @Value("${kafka.worker-notifications.topic}")
    private String serviceProviderNotificationsTopic;

    @Value("${kafka.consumer-notifications.topic}")
    private String consumerNotificationsTopic;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> kafkaProducerConfigs = new HashMap<>();
        kafkaProducerConfigs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        kafkaProducerConfigs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaProducerConfigs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(kafkaProducerConfigs);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
