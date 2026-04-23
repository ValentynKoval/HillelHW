package HW261.app;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        Address address = new Address("Київ", "Хрещатик", "10");
        user.setAddress(address);

        System.out.println("Адреса користувача: " + user.getAddress());
        System.out.println("Адреса належить User: " + (user.getAddress() == address));
    }
}
