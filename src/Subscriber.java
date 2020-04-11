import java.util.HashMap;
import java.util.Map;

public class Subscriber {
    private Map<String, String> phonebook = new HashMap();

    public String get(String  name) {
        return phonebook.get(name);
    }

    public void add(String name, String telephone){
        if(phonebook.containsKey(name)){
            phonebook.put(name, phonebook.get(name) + ", " + telephone);
        }else {
            phonebook.put(name, telephone);
        }
    }
}
