package com.orchestrator.debez.conf.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orchestrator.debez.conf.properties.KafkaProperties;

@Service
public class KafkaEndpointBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEndpointBuilder.class);
    
    private final KafkaProperties properties;

    @Autowired
    public KafkaEndpointBuilder(KafkaProperties properties) {
        this.properties = properties;
    }

    public String buildKafkaEndpoint(String clientId) {
        StringBuilder kafkaEndpoint = new StringBuilder();

        kafkaEndpoint.append("kafka:").append(properties.getTopic())
                .append("?brokers=").append(properties.getBrokers())
                .append("&clientId=").append(clientId)
                .append("&keySerializer=").append(properties.getKeySerializer())
                .append("&valueSerializer=").append(properties.getValueSerializer())
                .append("&requestRequiredAcks=").append(properties.getRequestRequiredAcks())
                .append("&compressionCodec=").append(properties.getCompressionCodec())
                .append("&retries=").append(properties.getRetries())
                .append("&maxInFlightRequest=").append(properties.getMaxInFlightRequest())
                .append("&enableIdempotence=").append(properties.isEnableIdempotence())
                .append("&lingerMs=").append(properties.getLingerMs());

        LOGGER.info("Kafka producer configuration is: {}", kafkaEndpoint);

        return kafkaEndpoint.toString();
    }
}
