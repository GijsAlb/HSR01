package Test;

import java.util.ArrayList;

public class CloneArrayListTest {
    
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        strings.add("d");
        ArrayList<String> clone = new ArrayList<>(strings);
        
        for(String i : clone) {
            System.out.println(i);
        }
    }
    
}
