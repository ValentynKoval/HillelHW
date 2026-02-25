package HW152.app;

import java.util.HashMap;
import java.util.Map;

public class DataRepository {
    public Map<String, String> getData() {
        Map<String, String> map = new HashMap<>();
        map.put("dog17", "Dog");
        map.put("cat55", "Cat");
        map.put("mos34", "Mouse");
        map.put("fox41", "Fox");
        return map;
    }
}
