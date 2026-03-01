package HW181.app;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        Optional<User> findById1 = userRepository.findUserById(1);
        Optional<User> findById6 = userRepository.findUserById(6);
        if (findById1.isPresent()) {
            System.out.println("Користувач знайдений за id=1: " + findById1.get());
        } else {
            System.out.println("Користувача не знайдено");
        }
        findById6.ifPresent((u) -> System.out.println("Користувач знайдений за id=6: " + u));

        Optional<User> findByEmail1 = userRepository.findUserByEmail("john@gmail.com");
        Optional<User> findByEmail2 = userRepository.findUserByEmail("jake@gmail.com");
        if (findByEmail1.isPresent()) {
            System.out.println("Користувач знайдений за email: " + findByEmail1.get());
        } else {
            System.out.println("Користувача не знайдено");
        }
        findByEmail2.ifPresent((u) -> System.out.println("Користувач знайдений за email" + u));

        userRepository.findAllUsers().ifPresent(u ->
                System.out.println("Кількість користувачів: " + u.stream().count()));
    }
}
