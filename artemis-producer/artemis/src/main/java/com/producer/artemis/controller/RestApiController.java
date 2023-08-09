package com.producer.artemis.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.producer.artemis.dto.User;
import com.producer.artemis.exception.ArtemisSendException;
import com.producer.artemis.jms.ArtemisProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RestApiController {

    private final ArtemisProducer artemisProducer;

    @PostMapping(value="/v1/produce")
    public ResponseEntity<List<User>> produce(@RequestBody List<User> listOfUsers) {
        log.info("in ingresso al controller la richiesta : {}", listOfUsers.toString());
        artemisProducer.send(listOfUsers);
        return new ResponseEntity<>(listOfUsers, HttpStatus.OK);
    }


    @ExceptionHandler(ArtemisSendException.class)
    public ResponseEntity<String> handleJmsException(ArtemisSendException e) {
    	log.error("errore artemis durante la produzione della queue : {}", e.getMessage());

        return new ResponseEntity<>("Errore JMS: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
