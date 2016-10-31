package com.home.initiator.spring;

import com.home.initiator.app.Initiator;
import com.home.initiator.app.InitiatorApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import quickfix.*;


@Configuration
public class Config {

    private static final String INITIATOR_CFG = "initiator.cfg";

    @Bean (initMethod = "start", destroyMethod = "stop")
    public Initiator initiator() throws ConfigError {
        SessionSettings initiatorSettings = new SessionSettings(INITIATOR_CFG);
        Application initiatorApplication = new InitiatorApplication();
        FileStoreFactory fileStoreFactory = new FileStoreFactory(initiatorSettings);
        FileLogFactory fileLogFactory = new FileLogFactory(initiatorSettings);
        MessageFactory messageFactory = new DefaultMessageFactory();
        SocketInitiator socketInitiator = new SocketInitiator
                (initiatorApplication, fileStoreFactory,
                initiatorSettings, fileLogFactory, messageFactory);
        return new Initiator(socketInitiator);
    }

}
