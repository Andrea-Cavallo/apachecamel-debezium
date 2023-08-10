package com.wes.camel.conf;

import static com.wes.camel.utils.Constants.DIRECT_PUBLISHER;
import static com.wes.camel.utils.Constants.DRIVER_CLASS_NAME;
import static com.wes.camel.utils.Constants.ENCRYPT_ETC;

import javax.sql.DataSource;

import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ConnectorConfig {

	@Value("${camel.sqlserver.serverName}")
    private String serverName;

    @Value("${camel.sqlserver.serverPort}")
    private int serverPort;

    @Value("${camel.sqlserver.username}")
    private String username;

    @Value("${camel.sqlserver.password}")
    private String password;

    @Value("${camel.sqlserver.databaseName}")
    private String databaseName;

    @Value("${camel.sqlserver.query}")
    private String query;


    /**
     * Crea una nuova rotta utilizzando la query specificata nell application.properties.
     *  La rotta parte dalla query SQL e indirizza i risultati al "direct:publisher".
     * 
     * @return Un oggetto RouteBuilder che definisce la rotta.
     * @author A.Cavallo
     */
    @Bean
    public RouteBuilder route() {
        return new RouteBuilder() {
            public void configure() {
                from("sql:" + query + "?dataSource=#dataSource")
                	.to(DIRECT_PUBLISHER);
            }
        };
    }
    /**
     * Configura e crea un DataSource per la connessione al database SQL Server.
     * Le credenziali e altri dettagli della connessione sono definiti come proprietà dell'oggetto BasicDataSource.
     * 
     * @return Un oggetto DataSource configurato per la connessione al database.
     */
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(DRIVER_CLASS_NAME);
        ds.setUrl("jdbc:sqlserver://" + serverName + ":" + serverPort + ";databaseName=" + databaseName +ENCRYPT_ETC);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }
}

