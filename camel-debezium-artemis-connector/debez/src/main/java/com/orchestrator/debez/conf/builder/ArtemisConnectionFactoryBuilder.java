package com.orchestrator.debez.conf.builder;


import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.orchestrator.debez.conf.properties.ArtemisProperties;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

@Service
public class ArtemisConnectionFactoryBuilder {

    private final ArtemisProperties properties;
    private static final String JMS_QUEUE_CONSUMER_CONF_TEMPLATE = "jms:queue:%s?connectionFactory=#artemisConnectionFactory";

    @Autowired
    public ArtemisConnectionFactoryBuilder(ArtemisProperties properties) {
        this.properties = properties;
    }

    @Bean
    public ConnectionFactory artemisConnectionFactory() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(properties.getBrokerUrl());
        connectionFactory.setUser(properties.getUser());
        connectionFactory.setPassword(properties.getPassword());
        return connectionFactory;
    }
    

    public String buildJmsQueueEndpoint() {
        return String.format(JMS_QUEUE_CONSUMER_CONF_TEMPLATE, properties.getTopicName());
    }
}
