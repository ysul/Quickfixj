package com.home.initiator.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.ConfigError;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SocketInitiator;
import quickfix.fix44.Message;

public class Initiator {
    private static final Logger LOG = LoggerFactory.getLogger(Initiator.class);
    private static final int WAIT_TIME = 5000;

    private final SocketInitiator socketInitiator;
    private Session session;
    private SessionID sessionId;

    public Initiator(SocketInitiator socketInitiator) {
        this.socketInitiator = socketInitiator;
    }

    public void start() {
        LOG.info("starting initiator...");
        try {
            socketInitiator.start();
            sessionId = socketInitiator.getSessions().get(0);
            session = Session.lookupSession(sessionId);
            logon();
        } catch (ConfigError configError) {
            LOG.error("failed to start initiator", configError);
            throw new RuntimeException(configError);
        }
    }

    public void stop() {
        LOG.info("stopping initiator...");
        session.logout();
        socketInitiator.stop();
    }

    public void sendMessage(Message message) {
        session.send(message);
    }

    private void logon() {
        LOG.info("waiting to logon...");
        while (!session.isLoggedOn()) {
            try {
                LOG.info("Still not logged in. Waiting...");
                Thread.sleep(WAIT_TIME);
            } catch (InterruptedException e) {
                LOG.warn("logon interrupted");
                return;
            }
        }
        LOG.info("logged on.");
    }
}
