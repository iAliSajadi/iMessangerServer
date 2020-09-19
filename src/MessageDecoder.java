import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;

public class MessageDecoder implements Decoder.Text<Message> {

//    @Override
//    public iMessage decode(Reader reader) throws DecodeException, IOException {
//        iMessage message = new iMessage();
//        try {
//            JsonReader jsonReader = Json.createReader(reader);
//            JsonObject iMessageJson = jsonReader.readObject();
//            message.setMessage(iMessageJson.getString("message"));
//            System.out.println(iMessageJson.toString());
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return message;
//    }

    @Override
    public Message decode(String jsonMessage) throws DecodeException {

        System.out.println(jsonMessage);
        JsonObject jsonObject = Json.createReader(new StringReader(jsonMessage)).readObject();
        Message message = new Message();
        message.setMessage(jsonObject.getString("message"));

        return message;

    }

    @Override
    public boolean willDecode(String jsonMessage) {
        try {
            // Check if incoming message is valid JSON
            System.out.println(jsonMessage);
            Json.createReader(new StringReader(jsonMessage)).readObject();
            return true;
        } catch (Exception ex) {
            System.out.println("Incoming message isn't a valid JSON");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        System.out.println("MessageDecoder -init method called");
    }

    @Override
    public void destroy() {
        System.out.println("MessageDecoder - destroy method called");
    }
}
