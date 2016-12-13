package com.home.initiator.session;

/**
 * Created by tothkornel on 2016. 12. 08..
 */
public enum SessionState {
    CONNECT,
    DISCONNECT,
    LOGON,
    LOGOUT,
    RESET,
    REFRESH,
    MISSED_HEARTBEAT,
    HEARTBEAT_TIMEOUT
}
