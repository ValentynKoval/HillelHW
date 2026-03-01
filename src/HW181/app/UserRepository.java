package HW181.app;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    List<User> users = Arrays.asList(
            new User(1, "John", "john@gmail.com"),
            new User(2, "Mike", "mike@gmail.com"),
            new User(3, "Jessy", "jessy@gmail.com"),
            new User(4, "Don", "don@gmail.com"),
            new User(5, "Max", "max@gmail.com"));

    public Optional<User> findUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public Optional<User> findUserByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    public Optional<List<User>> findAllUsers() {
        return Optional.of(users);
    }
}
