package com.orchestrator.debez.routes;

import javax.jms.JMSException;

import org.apache.camel.LoggingLevel;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.kafka.clients.producer.BufferExhaustedException;
import org.apache.kafka.common.errors.InterruptException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.errors.RecordTooLargeException;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.errors.TimeoutException;

import com.orchestrator.debez.conf.builder.KafkaEndpointBuilder;
import com.orchestrator.debez.exception.InternalException;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class BaseRoute extends RouteBuilder {
	
    protected final KafkaEndpointBuilder kafkaEndpointBuilder;  

	@Override
	public void configure() {

		onException();


	}

	private void onException() {
		
		onException(InternalException.class)
			.handled(true)
			.log(LoggingLevel.ERROR,"#### Internal Exception  Thrown  ####");
		
		onException(SerializationException.class)
			.handled(true)
			.log(LoggingLevel.ERROR,"#### Serialization Exception  Thrown  ####");

		onException(BufferExhaustedException.class)
			.handled(true)
			.log(LoggingLevel.ERROR,"#### BufferExhausted Exception  Thrown  ####");

		onException(TimeoutException.class)
			.handled(true)
			.log(LoggingLevel.ERROR,"#### Timeout Exception  Thrown  ####");

		onException(ProducerFencedException.class)
			.handled(true)
			.log(LoggingLevel.ERROR,"#### ProducerFenced Exception  Thrown  ####");

		onException(InterruptException.class)
			.handled(true)
			.log(LoggingLevel.ERROR,"#### Interrupt Exception  Thrown  ####");

		onException(RecordTooLargeException.class)
			.handled(true)
			.log(LoggingLevel.ERROR,"#### RecordTooLarge Exception  Thrown  ####");			
		
		onException(JMSException.class)
			.handled(true)
			.log(LoggingLevel.ERROR, "#### JMSException Exception Thrown  ####");

		onException(Exception.class)
			.handled(true)
			.onWhen(skipSpecifiedException())
			.log(LoggingLevel.ERROR, "#### Generic Exception Thrown  ####");

	}



	private Predicate skipSpecifiedException() {
		return exchange -> {return
		        (      !(exchange.getException() instanceof TimeoutException) 
		        );};
	}
	
	
}
