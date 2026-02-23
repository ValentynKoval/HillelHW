package HW141.app;

import java.util.ArrayList;
import java.util.List;

public class DataRepository {
    public List<String> getData() {
        List<String> list = new ArrayList<>(List.of("Alice", "Bob", "Lucy", "Denis", "Tom"));
        return list;
    }
}
