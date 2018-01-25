package be.brickrevolution.websocked;

import javax.websocket.Session;

public interface Websocket {

    public void onOpen(Session newSession);

    public void onClose(Session oldSession);

    public String onMessage(String message, Session in);

    public void sendToSession(Session out, String message);
}
