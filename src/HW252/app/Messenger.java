package HW252.app;

import java.util.ArrayList;
import java.util.List;

public class Messenger {
    private final List<Observer> observers = new ArrayList<Observer>();

    public void addObserver(Observer observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void sendMessageToAll(String message) {
        notifyObservers(message);
    }

    public void sendSpecialMessage(String message) {
        notifyObservers(message);
    }

    public List<Observer> getObservers() {
        return observers;
    }
}
