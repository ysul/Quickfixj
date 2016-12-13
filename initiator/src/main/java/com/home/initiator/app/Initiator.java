package com.home.initiator.app;

import com.home.initiator.session.SessionState;
import com.home.initiator.session.SessionStateSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.ConfigError;
import quickfix.SocketInitiator;

import java.util.concurrent.CountDownLatch;

import static com.home.initiator.session.SessionState.LOGON;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Initiator implements SessionStateSubscriber {
    private static final Logger LOG = LoggerFactory.getLogger(Initiator.class);
    private static final int WAIT_TIME = 15;

    private final SocketInitiator socketInitiator;
    private final CountDownLatch latch;

    public Initiator(SocketInitiator socketInitiator) {
        this.socketInitiator = socketInitiator;
        this.latch = new CountDownLatch(1);
    }

    public void start() {
        LOG.info("starting initiator...");
        try {
            socketInitiator.start();
            waitingForLogon();
        } catch (ConfigError configError) {
            LOG.error("failed to start initiator", configError);
            throw new RuntimeException(configError);
        }
    }

    public void stop() {
        LOG.info("stopping initiator...");
        socketInitiator.stop();
    }

    @Override
    public void update(SessionState sessionState) {
        if (LOGON == sessionState) {
            latch.countDown();
        }
    }

    private void waitingForLogon() {
        LOG.info("waiting for logon...");
        try {
            if (latch.await(WAIT_TIME, SECONDS)) {
                LOG.info("logged on.");
                return;
            }
        } catch (InterruptedException e) {
            LOG.warn("interrupted!");
        }
        LOG.error("logon failed within {} s", WAIT_TIME);
    }

}
