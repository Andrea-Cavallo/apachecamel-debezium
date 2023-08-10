package com.orchestrator.debez.routes;

import static com.orchestrator.debez.utils.Constants.CREATE;
import static com.orchestrator.debez.utils.Constants.CREATE_LOG;
import static com.orchestrator.debez.utils.Constants.DELETE;
import static com.orchestrator.debez.utils.Constants.DELETE_LOG;
import static com.orchestrator.debez.utils.Constants.DIRECT_SER_AND_PRODUCE;
import static com.orchestrator.debez.utils.Constants.READ;
import static com.orchestrator.debez.utils.Constants.READ_LOG;
import static com.orchestrator.debez.utils.Constants.SQLSERVER_CLIENT_ID;
import static com.orchestrator.debez.utils.Constants.START_DEBEZIUM_LOG;
import static com.orchestrator.debez.utils.Constants.UPDATE;
import static com.orchestrator.debez.utils.Constants.UPDATE_LOG;

import org.apache.camel.LoggingLevel;
import org.apache.camel.component.debezium.DebeziumConstants;
import org.springframework.stereotype.Component;

import com.orchestrator.debez.conf.builder.DebeziumConfigBuilder;
import com.orchestrator.debez.conf.builder.KafkaEndpointBuilder;
import com.orchestrator.debez.processor.DebeziumProcessor;

@Component
public class DebeziumRoute extends BaseRoute {

    private final DebeziumProcessor serializeDebeziumProcessor;
    private final DebeziumConfigBuilder debeziumConfigBuilder;

	
	public DebeziumRoute(KafkaEndpointBuilder kafkaProdConfig,DebeziumProcessor serializeDebeziumProcessor,DebeziumConfigBuilder debeziumConfigBuilder ) {
		super(kafkaProdConfig);
		this.serializeDebeziumProcessor=serializeDebeziumProcessor;	
		this.debeziumConfigBuilder = debeziumConfigBuilder;
		}
	
	
	@Override
	public void configure() {

		super.configure();
		debeziumRoute();
		

	}
	private void debeziumRoute() {
		
		from(debeziumConfigBuilder.buildDebeziumConnectorString())
				.log(START_DEBEZIUM_LOG)
				.choice()
					.when(header(DebeziumConstants.HEADER_OPERATION).isEqualTo(CREATE))
						.log(LoggingLevel.INFO, CREATE_LOG)
						.to(DIRECT_SER_AND_PRODUCE)
					.when(header(DebeziumConstants.HEADER_OPERATION).isEqualTo(READ))
						.log(LoggingLevel.INFO, READ_LOG)
						.to(DIRECT_SER_AND_PRODUCE)
					.when(header(DebeziumConstants.HEADER_OPERATION).isEqualTo(UPDATE))
						.log(LoggingLevel.INFO, UPDATE_LOG)
						.to(DIRECT_SER_AND_PRODUCE)
					.when(header(DebeziumConstants.HEADER_OPERATION).isEqualTo(DELETE))
						.log(LoggingLevel.INFO, DELETE_LOG)
						.to(DIRECT_SER_AND_PRODUCE)
				.endChoice();
		
		
		from(DIRECT_SER_AND_PRODUCE)
			.process(serializeDebeziumProcessor)
			.log(LoggingLevel.INFO,"Body of Topic Serialized is : ${body}")
			.to(kafkaEndpointBuilder.buildKafkaEndpoint(SQLSERVER_CLIENT_ID))
			.log(LoggingLevel.INFO,"Topic with clientId :SQLSERVER  successfully produced");

	}
}
