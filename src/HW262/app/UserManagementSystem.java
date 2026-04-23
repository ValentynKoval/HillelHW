package HW262.app;

import java.util.ArrayList;
import java.util.List;

public class UserManagementSystem {
    public static void main(String[] args) {
        // Створення репозиторію користувачів
        UserRepository userRepository = new UserRepositoryImpl();

        // Додавання користувачів
        User user1 = new User("Іван", "ivan@example.com", "password1");
        userRepository.addUser(user1);

        User user2 = new User("Марія", "maria@example.com", "password2");
        userRepository.addUser(user2);

        // Видалення користувача
        userRepository.removeUser(user1);

        // Отримання користувача за email
        User user = userRepository.getUser("maria@example.com");
        if (user != null) {
            System.out.println("Знайдено користувача: " + user.getName());
        } else {
            System.out.println("Користувача з таким email не знайдено.");
        }
    }
}