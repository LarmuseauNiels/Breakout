package be.brickrevolution.websocked;

import be.brickrevolution.Playfield.Game;
import be.brickrevolution.Playfield.Playfield;
import be.brickrevolution.util.BreakoutException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/endpoint")
public class BasicWebsocked implements Websocket {

    private static final Map<Session, Game> usersAndTheirSessions
            = Collections.synchronizedMap(new HashMap<Session, Game>());

    @OnOpen
    @Override
    public void onOpen(Session newSession) {
        Game pf = generateNewPlayfield(newSession);
        usersAndTheirSessions.put(newSession, pf);
    }

    @OnClose
    @Override
    public void onClose(Session oldSession) {
        Game pf = usersAndTheirSessions.get(oldSession);
        pf.stop();
        usersAndTheirSessions.remove(oldSession);
    }

    @OnMessage
    @Override
    public String onMessage(String message, Session in) {
        Game g = usersAndTheirSessions.get(in);
        String arr[] = message.split(" ", 2);
        switch (arr[0]) {
            case "moveleft":
                g.getPlayfield().movePallet(g.getPlayfield().getBotumPallet(), false);
                break;
            case "moveright":
                g.getPlayfield().movePallet(g.getPlayfield().getBotumPallet(), true);
                break;
            case "start":
                g.start();
                break;
            case "load":
                g.getPlayfield().setDifficulty(arr[1]);
                g.laatlevelin();
                break;
            case "stop":
                g.stop();
                break;
            case "nextlevel":
                g.getPlayfield().reset();
                break;
            case "uuid":
                g.getPlayfield().setPlayerUuid(arr[1]);
                break;
            default:
                break;
        }
        return null;
    }

    @Override
    public void sendToSession(Session out, String message) {
        try {
            if (out.isOpen()) {
                out.getBasicRemote().sendText(message);
            }
        } catch (IOException ex) {
            throw new BreakoutException("Unable to send to session.", ex);
        }
    }

    private synchronized Game generateNewPlayfield(Session ses) {
        Game g = new Game(ses, this);
        g.setPlayfield(new Playfield(g));
        return g;
    }
}
