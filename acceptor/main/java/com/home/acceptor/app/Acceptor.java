package com.home.acceptor.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.ConfigError;
import quickfix.SocketAcceptor;

public class Acceptor {
    private final static Logger LOG = LoggerFactory.getLogger(Acceptor.class);

    private final SocketAcceptor socketAcceptor;

    public Acceptor(SocketAcceptor socketAcceptor) {
        this.socketAcceptor = socketAcceptor;
    }

    public void start() {
        LOG.info("starting acceptor...");
        try {
            socketAcceptor.start();
        } catch (ConfigError configError) {
            System.out.println();
        }
    }

    public void stop() {
        LOG.info("stopping acceptor...");
        socketAcceptor.stop();
    }
}
