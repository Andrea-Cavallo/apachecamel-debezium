package com.producer.artemis.jms;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.producer.artemis.dto.User;
import com.producer.artemis.exception.ArtemisSendException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ArtemisProducer {
	
    private final JmsTemplate jmsTemplate;
    private final String destinationQueue;

    public ArtemisProducer(JmsTemplate jmsTemplate, 
                           @Value("${jms.queue.destination}") String destinationQueue) {
        this.jmsTemplate = jmsTemplate;
        this.destinationQueue = destinationQueue;
    }

    public void send(List<User> listOfUsers) {
        try {
        	log.info("dentro il service -> sto per inviare la listaDiUsers");
        	ObjectMapper objectMapper = new ObjectMapper();
        	String serializedUsers ="";
        	serializedUsers = returnSerializedUsers(listOfUsers, objectMapper, serializedUsers);
        	log.info("lista user serializzata {}", serializedUsers);
            jmsTemplate.convertAndSend(destinationQueue, serializedUsers);

        } catch (JmsException e) {
        	log.error("JmsException Throwed -> {}", e.getMessage());
            throw new ArtemisSendException("Errore durante l'invio a Artemis", e);
        }
    }

	private String returnSerializedUsers(List<User> listOfUsers, ObjectMapper objectMapper, String serializedUsers) {
		try {
			 serializedUsers =  objectMapper.writeValueAsString(listOfUsers);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return serializedUsers;
	}
}

