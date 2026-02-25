package HW142.app;

import java.util.ArrayList;
import java.util.List;

public class DataRepository {
    // Метод повертає список тваринок
    public List<String> getData() {
        List<String> list = new ArrayList<>();
        list.add("cat");
        list.add("dog");
        list.add("mouse");
        list.add("fox");
        return list;
    }
}
