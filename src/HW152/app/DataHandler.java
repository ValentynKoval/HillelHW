package HW152.app;

import java.util.Map;

public class DataHandler {
    Map<String, String> map = new DataRepository().getData();

    public String getById(String id) {
        if (map.containsKey(id)) {
            return String.format("ANIMAL: id %s, name %s",
                    id, map.get(id));
        } else return "No data!";
    }
}
