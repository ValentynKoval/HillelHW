package HW252.app;

public class Main {
    public static void main(String[] args) {
        Messenger messenger = new Messenger();

        MessageObserver observer1 = new MessageObserver("User1");
        MessageObserver observer2 = new MessageObserver("User2");
        MessageObserver observer3 = new MessageObserver("User3");

        messenger.addObserver(observer1);
        messenger.addObserver(observer2);
        messenger.addObserver(observer3);

        messenger.sendMessageToAll("Це тестове повідомлення.");
        messenger.sendMessageToAll("Ще одне повідомлення для всіх.");
        messenger.sendSpecialMessage("Нагадування про технічні роботи.");

        messenger.removeObserver(observer2);
        messenger.sendMessageToAll("Повідомлення після відписки User2.");

        System.out.println("User1 отримав(ла): " + observer1.getMessageReceived().size() + " повідомлень");
        System.out.println("User2 отримав(ла): " + observer2.getMessageReceived().size() + " повідомлень");
        System.out.println("User3 отримав(ла): " + observer3.getMessageReceived().size() + " повідомлень");
    }
}
