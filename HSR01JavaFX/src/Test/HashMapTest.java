package Test;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class HashMapTest {
    
    public static void main(String[] args) {
        DatabaseConnectionTest dbc = new DatabaseConnectionTest();
        LinkedHashMap dbHashMap = dbc.connect();
//        System.out.println(dbHashMap.entrySet());
        Iterator it1 = dbHashMap.entrySet().iterator();
        while (it1.hasNext()) {
            Map.Entry pair1 = (Map.Entry)it1.next();
//            System.out.println(pair1.getKey() + " " + pair1.getValue());
            LinkedHashMap tempHashMap = (LinkedHashMap) pair1.getValue();
            Iterator it2 = tempHashMap.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry pair2 = (Map.Entry) it2.next();
                System.out.println(pair2.getKey() + " " + pair2.getValue());
                it2.remove();
            }
            System.out.println();
            it1.remove();
        }
    }
    
}
