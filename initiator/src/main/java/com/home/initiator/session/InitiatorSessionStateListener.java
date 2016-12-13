package com.home.initiator.session;

import quickfix.SessionStateListener;

import java.util.ArrayList;
import java.util.List;

import static com.home.initiator.session.SessionState.CONNECT;
import static com.home.initiator.session.SessionState.DISCONNECT;
import static com.home.initiator.session.SessionState.HEARTBEAT_TIMEOUT;
import static com.home.initiator.session.SessionState.LOGON;
import static com.home.initiator.session.SessionState.LOGOUT;
import static com.home.initiator.session.SessionState.MISSED_HEARTBEAT;
import static com.home.initiator.session.SessionState.REFRESH;
import static com.home.initiator.session.SessionState.RESET;

public class InitiatorSessionStateListener implements SessionStateListener {

    private final List<SessionStateSubscriber> subscribers = new ArrayList<>();

    public void addSubscriber(SessionStateSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void onConnect() {
        updateSubscribers(CONNECT);
    }

    public void onDisconnect() {
        updateSubscribers(DISCONNECT);
    }

    public void onLogon() {
        updateSubscribers(LOGON);
    }

    public void onLogout() {
        updateSubscribers(LOGOUT);
    }

    public void onReset() {
        updateSubscribers(RESET);
    }

    public void onRefresh() {
        updateSubscribers(REFRESH);
    }

    public void onMissedHeartBeat() {
        updateSubscribers(MISSED_HEARTBEAT);
    }

    public void onHeartBeatTimeout() {
        updateSubscribers(HEARTBEAT_TIMEOUT);
    }

    private void updateSubscribers(SessionState state) {
        for (SessionStateSubscriber subscriber : subscribers) {
            subscriber.update(state);
        }
    }
}
