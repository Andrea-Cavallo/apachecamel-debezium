package com.orchestrator.debez.conf.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class KafkaProperties {

	   @Value("${camel.kafka.topic}")
	    private String topic;

	    @Value("${camel.kafka.brokers}")
	    private String brokers;

	    @Value("${camel.kafka.keySerializer}")
	    private String keySerializer;

	    @Value("${camel.kafka.valueSerializer}")
	    private String valueSerializer;

	    @Value("${camel.kafka.requestRequiredAcks}")
	    private String requestRequiredAcks;

	    @Value("${camel.kafka.compressionCodec}")
	    private String compressionCodec;

	    @Value("${camel.kafka.retries}")
	    private int retries;

	    @Value("${camel.kafka.maxInFlightRequest}")
	    private int maxInFlightRequest;

	    @Value("${camel.kafka.enableIdempotence}")
	    private boolean enableIdempotence;

	    @Value("${camel.kafka.lingerMs}")
	    private int lingerMs;

 
}
