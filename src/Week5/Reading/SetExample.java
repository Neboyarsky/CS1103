package Week5.Reading;

import java.util.HashSet;
import java.util.Set;

public class SetExample {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("London");
        set.add("Paris");
        set.add("Moscow");
        System.out.println(set);
        for (Object element: set) {
            System.out.println(element.toString());
        }
    }
}
