package com.home.acceptor.spring;

import com.home.acceptor.app.Acceptor;
import com.home.acceptor.app.AcceptorApplication;
import com.home.base.session.SessionStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import quickfix.*;

@Configuration
public class Config {

    private static final String ACCEPTOR_CFG = "acceptor.cfg";

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Acceptor acceptor() throws ConfigError {
        SessionSettings executorSettings = new SessionSettings(ACCEPTOR_CFG);
        Application application = new AcceptorApplication(new SessionStore());
        FileStoreFactory fileStoreFactory =
                new FileStoreFactory(executorSettings);
        MessageFactory messageFactory = new DefaultMessageFactory();
        FileLogFactory fileLogFactory = new FileLogFactory(executorSettings);
        SocketAcceptor socketAcceptor = new SocketAcceptor(
                application,
                fileStoreFactory,
                executorSettings,
                fileLogFactory,
                messageFactory);
        return new Acceptor(socketAcceptor);
    }
}
