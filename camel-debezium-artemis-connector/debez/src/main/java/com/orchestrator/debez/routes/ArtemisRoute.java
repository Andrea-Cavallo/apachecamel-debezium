package com.orchestrator.debez.routes;

import static com.orchestrator.debez.utils.Constants.ARTEMIS_CLIENT_ID;
import static com.orchestrator.debez.utils.Constants.START_ARTEMIS_QUEUE_LOG;

import org.apache.camel.LoggingLevel;
import org.springframework.stereotype.Component;

import com.orchestrator.debez.conf.builder.ArtemisConnectionFactoryBuilder;
import com.orchestrator.debez.conf.builder.KafkaEndpointBuilder;
import com.orchestrator.debez.processor.ArtemisQueueProcessor;

@Component
public class ArtemisRoute extends BaseRoute {
	
    private final ArtemisQueueProcessor serializeArtemisProcessor;
    private final ArtemisConnectionFactoryBuilder artemisConnectionFactoryBuilder;
    
	public ArtemisRoute(KafkaEndpointBuilder kafkaEndpointBuilder,ArtemisQueueProcessor serializeArtemisProcessor
	,ArtemisConnectionFactoryBuilder artemisEndpointBuilder		
			) {
		super(kafkaEndpointBuilder);
		this.serializeArtemisProcessor=serializeArtemisProcessor;	
		this.artemisConnectionFactoryBuilder = artemisEndpointBuilder;
		}
	
    @Override
    public void configure() {	
		super.configure();
    	artemisQueueRoute();
    }
    
    
	private void artemisQueueRoute() {
			
			from(artemisConnectionFactoryBuilder.buildJmsQueueEndpoint())
					.log(START_ARTEMIS_QUEUE_LOG)
				    .log(LoggingLevel.INFO, "Incoming Queue From Artemis As String is: ${body}")
				    .process(serializeArtemisProcessor)
					.log("Body of Topic is   : ${body}")
					.to(kafkaEndpointBuilder.buildKafkaEndpoint(ARTEMIS_CLIENT_ID))
					.log(LoggingLevel.INFO,"Topic with clientId :ARTEMIS  successfully produced");
	
		}
}
