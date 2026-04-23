package HW262.app;

import java.util.ArrayList;
import java.util.List;

class UserRepositoryImpl implements UserRepository {
    private List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
        System.out.println("Користувач " + user.getName() + " доданий до системи.");
    }

    @Override
    public void removeUser(User user) {
        users.remove(user);
        System.out.println("Користувач " + user.getName() + " видалений з системи.");
    }

    @Override
    public User getUser(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }
}