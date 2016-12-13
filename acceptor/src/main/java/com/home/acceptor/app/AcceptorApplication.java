package com.home.acceptor.app;

import com.home.base.session.SessionStore;
import quickfix.Application;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.RejectLogon;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;

/***
 * The Class TestTradeAppExecutor.
 * @author prabhat.jha
 */
public class AcceptorApplication implements Application {

    private final SessionStore sessionStore;

    public AcceptorApplication(SessionStore sessionStore) {
        this.sessionStore = sessionStore;
    }

    /** (non-Javadoc)
     * @see quickfix.Application#onCreate(quickfix.SessionID)
     */
    public void onCreate(SessionID sessionId) {
    }

    /** (non-Javadoc)
     * @see quickfix.Application#onLogon(quickfix.SessionID)
     */
    public void onLogon(SessionID sessionId) {
        sessionStore.storeSession(sessionId);
    }

    /** (non-Javadoc)
     * @see quickfix.Application#onLogout(quickfix.SessionID)
     */
    public void onLogout(SessionID sessionId) {
        sessionStore.removeSession(sessionId);
    }

    /** (non-Javadoc)
     * @see quickfix.Application#toAdmin(quickfix.Message, quickfix.SessionID)
     */
    public void toAdmin(Message message, SessionID sessionId) {
    }

    /** (non-Javadoc)
     * @see quickfix.Application#fromAdmin(quickfix.Message, quickfix.SessionID)
     */
    public void fromAdmin(Message message, SessionID sessionId)
            throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue,
            RejectLogon {
    }

    /** (non-Javadoc)
     * @see quickfix.Application#toApp(quickfix.Message, quickfix.SessionID)
     */
    public void toApp(Message message, SessionID sessionId) throws DoNotSend {
    }

    /** (non-Javadoc)
     * @see quickfix.Application#fromApp(quickfix.Message, quickfix.SessionID)
     */
    public void fromApp(Message message, SessionID sessionId)
            throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue,
            UnsupportedMessageType {
    }
}