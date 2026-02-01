package HW121.app;

public class MyThread extends Thread{
    private final DataHandler dataHandler;

    public MyThread(String name, DataHandler dataHandler) {
        super(name);
        this.dataHandler = dataHandler;
    }

    public void run() {
        dataHandler.getOutput();
    }
}
