package com.orchestrator.debez.conf.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter

public class DebeziumProperties {

    @Value("${debezium.databaseHostName}")
    private String databaseHostName;

    @Value("${debezium.databasePort}")
    private int databasePort;

    @Value("${debezium.databaseUser}")
    private String databaseUser;

    @Value("${debezium.databasePassword}")
    private String databasePassword;

    @Value("${debezium.databaseServerName}")
    private String databaseServerName;

    @Value("${debezium.includeSchemaChanges}")
    private boolean includeSchemaChanges;

    @Value("${debezium.databaseDbname}")
    private String databaseDbname;

    @Value("${debezium.tableWhitelist}")
    private String tableWhitelist;

    @Value("${debezium.offsetStorageFileName}")
    private String offsetStorageFileName;

    @Value("${debezium.databaseHistoryFileFilename}")
    private String databaseHistoryFileFilename;

}
