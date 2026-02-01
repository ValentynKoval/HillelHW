package HW112.app;

public class Main {
    public static void main(String[] args) {
        FileHandler handler = new FileHandler();
        String path = "files/info.txt";
        getOutput("FILE CONTENT: " + handler.readFromFile(path));
    }

    private static void getOutput(String output) {
        System.out.println(output);
    }
}
