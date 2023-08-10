package com.wes.camel.conf;


import javax.annotation.PostConstruct;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.wes.camel.routes.ConnectorRoute;

@Configuration
public class CamelConfig {

    @Autowired
    private CamelContext camelContext;

    @Autowired
    private ConnectorRoute connectorRoute;

    /**
     * Inizializza Camel aggiungendo le rotte specificate.
     * Questo metodo viene invocato automaticamente dopo la creazione dell'istanza della classe,
     * grazie all'annotazione @PostConstruct.
     * 
     * @throws Exception Se si verifica un errore durante l'aggiunta delle rotte a Camel.
     * @author A.Cavallo
     */
    @PostConstruct
    public void setupCamel() {
        try {
            camelContext.addRoutes(connectorRoute);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
