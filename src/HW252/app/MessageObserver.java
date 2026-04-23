package HW252.app;

import java.util.ArrayList;
import java.util.List;

public class MessageObserver extends Observer {
    private final User user;
    private final List<String> messagesReceived;

    public MessageObserver(String name) {
        this(new User(name));
    }

    public MessageObserver(User user) {
        this.user = user;
        this.messagesReceived = new ArrayList<String>();
    }

    @Override
    public void update(String message) {
        user.receiveMessage(message);
        messagesReceived.add(message);
    }

    public User getUser() {
        return user;
    }

    public List<String> getMessageReceived() {
        return messagesReceived;
    }

    public List<String> getMessagesReceived() {
        return messagesReceived;
    }
}

class User {
    private final String name;
    private final List<String> messagesReceived;

    public User(String name) {
        this.name = name;
        this.messagesReceived = new ArrayList<String>();
    }

    public void receiveMessage(String message) {
        messagesReceived.add(message);
        System.out.println(name + " отримав(ла) повідомлення: " + message);
    }

    public String getName() {
        return name;
    }

    public List<String> getMessagesReceived() {
        return messagesReceived;
    }
}
