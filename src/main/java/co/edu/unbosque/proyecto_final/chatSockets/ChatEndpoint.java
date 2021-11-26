package co.edu.unbosque.proyecto_final.chatSockets;

import co.edu.unbosque.proyecto_final.chatSockets.model.Message;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/chat/{role}/{username}", decoders = co.edu.unbosque.proyecto_final.chatSockets.MessageDecoder.class, encoders = co.edu.unbosque.proyecto_final.chatSockets.MessageEncoder.class)
public class ChatEndpoint {

    private Session session;
    private static final Set<ChatEndpoint> chatEndpoints = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();
    private static HashMap<String, String> roles = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username, @PathParam("role") String role) {

        this.session = session;
        chatEndpoints.add(this);
        users.put(session.getId(), username);
        roles.put(session.getId(), role);

        Message message = new Message();
        message.setFrom(username);
        message.setContent("Connected!");
        broadcast(message, "open");
    }

    @OnMessage
    public void onMessage(Session session, Message message) {
        message.setFrom(users.get(session.getId()));

        broadcast(message, "message");
    }

    @OnClose
    public void onClose(Session session) {
        chatEndpoints.remove(this);
        Message message = new Message();
        message.setFrom(users.get(session.getId()));
        message.setContent("Disconnected!");
        broadcast(message, "close");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    private static void broadcast(Message message, String type) {
        chatEndpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    if((type.equals("open") || type.equals("close")) && users.get(endpoint.session.getId()).equals(message.getFrom())){
                        System.out.println("Sending message to " + users.get(endpoint.session.getId()));
                        endpoint.session.getBasicRemote().sendObject(message);
                    }else if(type.equals("message") && (roles.get(endpoint.session.getId()).equals("official") || users.get(endpoint.session.getId()).equals(message.getFrom()))){
                        System.out.println("Sending message to " + users.get(endpoint.session.getId()));
                        endpoint.session.getBasicRemote().sendObject(message);
                    }
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}