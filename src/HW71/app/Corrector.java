package HW71.app;

public class Corrector {
    public static String handleData(String[] strs) {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (String str : strs) {
            if (str.contains("b")) {
                str = str.replace("b", "o");
            }
            count++;
            stringBuilder.append(count).append(") ").append(str).append("\n");
        }
        return stringBuilder.toString();
    }
}
