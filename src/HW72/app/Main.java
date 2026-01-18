package HW72.app;

public class Main {
    public static void main(String[] args) {
        getOutput(new Remover().handleData(
                new DataProvider().getData())
        );
    }

    private static void getOutput(String output) {
        System.out.println(output);
    }
}
