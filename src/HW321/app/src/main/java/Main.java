import coffee.order.CoffeeOrderBoard;
import coffee.order.Order;

public class Main {

    public static void main(String[] args) {
        CoffeeOrderBoard board = new CoffeeOrderBoard();

        System.out.println("Додаємо замовлення:");
        board.add("Alen");
        board.add("Yoda");
        board.add("Obi-van");
        board.add("John Snow");

        System.out.println("\nПоточна черга:");
        board.draw();

        System.out.println("\nВидаємо найближче замовлення:");
        Order deliveredFirst = board.deliver();
        System.out.println("Видано: " + deliveredFirst);

        System.out.println("\nЧерга після видачі першого замовлення:");
        board.draw();

        System.out.println("\nВидаємо замовлення з номером 3:");
        Order deliveredByNumber = board.deliver(3);
        System.out.println("Видано: " + deliveredByNumber);

        System.out.println("\nЧерга після видачі замовлення №3:");
        board.draw();

        System.out.println("\nДодаємо ще одне замовлення:");
        Order newOrder = board.add("Anakin");
        System.out.println("Додано: " + newOrder);

        System.out.println("\nФінальний стан черги:");
        board.draw();

        System.out.println("\nПеревірка обробки помилки:");
        try {
            board.deliver(999);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}