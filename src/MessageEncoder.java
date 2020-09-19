import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import static java.awt.SystemColor.info;

public class MessageEncoder implements Encoder.Text<Message> {
//    @Override
//    public void encode(iMessage message, Writer writer) throws EncodeException, IOException {
//        JsonObject iMessageJson = Json.createObjectBuilder()
//                .add("message", message.getMessage())
//                .build();
//        try {
//            JsonWriter jsonWriter = Json.createWriter(writer);
//            jsonWriter.writeObject(iMessageJson);
//            System.out.println(iMessageJson.toString());
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    @Override
    public String encode(Message message) throws EncodeException {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("message", message.getMessage())
                .build();
        return jsonObject.toString();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        System.out.println("MessageEncoder - init method called");
    }

    @Override
    public void destroy() {
        System.out.println("MessageEncoder - destroy method called");
    }
}
