package com.home.base.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.Session;
import quickfix.SessionID;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SessionStore {
    private final static Logger LOG = LoggerFactory.getLogger(SessionStore.class);

    private final ConcurrentMap<SessionID, Optional<Session>> sessions = new ConcurrentHashMap<>();

    public void storeSession(SessionID sessionId) {
        LOG.info("Storing session for sessionID {}", sessionId);
        sessions.put(sessionId, Optional.ofNullable(Session.lookupSession(sessionId)));
    }

    public Optional<Session> getSession(SessionID sessionID) {
        return sessions.get(sessionID);
    }

    public void removeSession(SessionID sessionId) {
        LOG.info("Removing session for sessionID {}", sessionId);
        sessions.remove(sessionId);
    }
}
