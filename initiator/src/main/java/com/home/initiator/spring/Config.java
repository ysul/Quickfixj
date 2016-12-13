package com.home.initiator.spring;

import com.home.base.session.SessionStore;
import com.home.initiator.app.Initiator;
import com.home.initiator.app.InitiatorApplication;
import com.home.initiator.session.InitiatorSessionStateListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import quickfix.*;


@Configuration
public class Config {

    private static final String INITIATOR_CFG = "initiator.cfg";

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Initiator initiator() throws ConfigError {
        SessionSettings initiatorSettings = new SessionSettings(INITIATOR_CFG);
        InitiatorSessionStateListener stateListener =
                new InitiatorSessionStateListener();
        InitiatorApplication initiatorApplication =
                new InitiatorApplication(new SessionStore(), stateListener);
        FileStoreFactory messageStoreFactory =
                new FileStoreFactory(initiatorSettings);
        FileLogFactory logFactory = new FileLogFactory(initiatorSettings);
        DefaultMessageFactory messageFactory = new DefaultMessageFactory();
        SocketInitiator socketInitiator = new SocketInitiator(
                initiatorApplication,
                messageStoreFactory,
                initiatorSettings,
                logFactory,
                messageFactory);
        Initiator initiator = new Initiator(socketInitiator);
        stateListener.addSubscriber(initiator);
        return initiator;
    }

}
