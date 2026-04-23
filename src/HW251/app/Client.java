package HW251.app;

public class Client {
    public void run() {
        TransportFactory carFactory = new CarFactory();
        TransportFactory planeFactory = new PlaneFactory();

        Transport car = carFactory.createTransport();
        Transport plane = planeFactory.createTransport();

        System.out.println("Перевірка створення об'єктів:");
        System.out.println("CarFactory створив Car: " + (car instanceof Car));
        System.out.println("PlaneFactory створив Plane: " + (plane instanceof Plane));
        System.out.println();

        System.out.println("Рух транспортних засобів:");
        car.move();
        plane.move();
    }
}
