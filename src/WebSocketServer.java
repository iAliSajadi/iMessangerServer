import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/hello")
public class WebSocketServer {

    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected...");
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(String message) {
        for (Session openSession : sessions) {
            try {
                openSession.getBasicRemote().sendText("I got your message");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Disconnected...");
        sessions.remove(session);
    }
}
