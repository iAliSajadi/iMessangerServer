import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint(
        value = "/chat",
        encoders = {MessageEncoder.class},
        decoders = {MessageDecoder.class}
        )
public class WebSocketServer {

    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected...");
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(Message message) {
        for (Session openSession : sessions) {
            try {
                System.out.println(message.toString());
//                openSession.getBasicRemote().sendText("I got your message");
                message.setMessage("Response to " + message.getMessage());
                openSession.getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException ex) {
                Logger.getLogger(WebSocketServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Disconnected...");
        sessions.remove(session);
    }
}
