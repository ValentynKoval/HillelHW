package HW261.app;

public class Address {
    private String city;
    private String street;
    private String houseNumber;

    public Address(String city, String street, String houseNumber) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "м. " + city + ", вул. " + street + ", буд. " + houseNumber;
    }
}
