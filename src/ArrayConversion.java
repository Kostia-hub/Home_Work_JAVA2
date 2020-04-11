
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayConversion {
    public static void main(String[] args) {
        List<String> wordSet = new ArrayList<>();
        wordSet.add("Михаил");
        wordSet.add("Анна");
        wordSet.add("Павел");
        wordSet.add("Анна");
        wordSet.add("Анна");
        wordSet.add("Михаил");
        wordSet.add("Павел");
        wordSet.add("Игорь");
        wordSet.add("Михаил");
        wordSet.add("Олег");
        wordSet.add("Александр");
        wordSet.add("Федор");
        wordSet.add("Арсен");
        wordSet.add("Евфросиния");
        wordSet.add("Ждана");
        wordSet.add("Олег");


        System.out.println(wordSet);
        System.out.println(createUniqueWordArray(wordSet));
        System.out.println(findRepeat(wordSet));
    }

    public static List<String> createUniqueWordArray(List<String> list){
        List<String> uniqueWord = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(uniqueWord.contains(list.get(i)) == false){
                uniqueWord.add(list.get(i));
            }
        }
        return uniqueWord;
    }
    public static Map<String, Integer> findRepeat(List<String> list){
        Map<String, Integer> repeatMap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            if(repeatMap.containsKey(list.get(i)) == false){
                repeatMap.put(list.get(i), 1);
            }else {
                repeatMap.put(list.get(i), repeatMap.get(list.get(i)) + 1);
            }
        }
        return repeatMap;
    }
}
