package HW42.app;

public class Main {
    public static void main(String[] args) {
        OutputFormatter formatter = new OutputFormatter();
        getOutput(formatter.getOutputData(getBuyer(getData())));
    }

    public static String[] getData() {
        return new String[]{"Alice", "Terra", "555 123-8596"};
    }

    public static Buyer getBuyer(String[] data) {
        return new Buyer(data[0], data[1], data[2]);
    }

    public static void getOutput(String output) {
        System.out.println(output);
    }
}
