package com.orchestrator.debez.conf.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orchestrator.debez.conf.properties.DebeziumProperties;

@Service
public class DebeziumConfigBuilder {

    private static final String SQLSERVER_CONNECTOR_NAME = "debezium-sqlserver:sql-connector";
	private final DebeziumProperties properties;

    @Autowired
    public DebeziumConfigBuilder(DebeziumProperties properties) {
        this.properties = properties;
    }

    public String buildDebeziumConnectorString() {
        StringBuilder sb = new StringBuilder(SQLSERVER_CONNECTOR_NAME);
        sb.append("?databaseHostName=").append(properties.getDatabaseHostName())
          .append("&databasePort=").append(properties.getDatabasePort())
          .append("&databaseUser=").append(properties.getDatabaseUser())
          .append("&databasePassword=").append(properties.getDatabasePassword())
          .append("&databaseServerName=").append(properties.getDatabaseServerName())
          .append("&includeSchemaChanges=").append(properties.isIncludeSchemaChanges())
          .append("&databaseDbname=").append(properties.getDatabaseDbname())
          .append("&tableWhitelist=").append(properties.getTableWhitelist())
          .append("&offsetStorageFileName=").append(properties.getOffsetStorageFileName())
          .append("&databaseHistoryFileFilename=").append(properties.getDatabaseHistoryFileFilename());

        return sb.toString();
    }
}
