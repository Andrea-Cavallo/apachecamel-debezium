package com.orchestrator.debez.conf.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class ArtemisProperties {

    @Value("${artemis.broker-url}")
    private String brokerUrl;

    @Value("${artemis.user}")
    private String user;

    @Value("${artemis.password}")
    private String password;

    @Value("${artemis.topicName}")
    private String topicName;
}
