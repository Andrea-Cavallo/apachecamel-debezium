package com.wes.camel.routes;
import static com.wes.camel.utils.Constants.DIRECT_PUBLISHER;
import static com.wes.camel.utils.Constants.UPDATE_QUERY;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wes.camel.model.OutboxEvent;
@Component
public class ConnectorRoute extends RouteBuilder {


	@Value("${camel.kafka.topic}")
    private String topic;

    @Value("${camel.kafka.brokers}")
    private String brokers;
    
    @Autowired
    private ObjectMapper objectMapper;

	    @Override
	    public void configure ()  {

	    onException();
	    
	    	/**
	    	 * <p>
	    	 * Il flusso comprende le seguenti fasi:
	    	 * <ul>
	    	 *     <li>Ricezione del messaggio dalla query -> application.properties.</li>
	    	 *     <li>Marshalling del corpo del messaggio in formato JSON.</li>
	    	 *     <li>Invio del messaggio al topic Kafka -> application.properties leggi nome del topic.</li>
	    	 *     <li>Elaborazione del messaggio per convertirlo nell'oggetto OutboxEvent.</li>
	    	 *     <li>Aggiornamento dello stato dell'evento Outbox nel database con published = true.</li>
	    	 * </ul>
	    	 */	
	    sqlServerSourceConnectorFlow();
	    
	    }
	    
		private void sqlServerSourceConnectorFlow() {
			
			from(DIRECT_PUBLISHER)
					.log(LoggingLevel.INFO, "Received Message from my Query: ${body}")
					.marshal().json(JsonLibrary.Jackson, OutboxEvent.class).convertBodyTo(String.class)
					
			
					// qua ci puo andare una qualsiasi logica di transcoding prima di inviare l evento
					.to("kafka:" + topic + "?brokers=" + brokers + "&clientId=pippo")
			
			        .log(LoggingLevel.INFO,"Outbox published is: ${body}")
			        .to(UPDATE_QUERY)
			        .log(LoggingLevel.INFO,"Outbox event with ID ${body.id} marked as published");
		
		}

		private void onException() {
			
			// per adesso non fanno niente poi capire come fare in caso di ecc
			onException(ConnectTimeoutException.class).handled(true)
					.log(LoggingLevel.ERROR, "#### ConnectTimeoutException thrown ####");

			onException(Exception.class)
					.handled(true)
					.log(LoggingLevel.ERROR, "#### Generic Exception Thrown  ####");			

		}
}

    
    
    
    
    
    
    


