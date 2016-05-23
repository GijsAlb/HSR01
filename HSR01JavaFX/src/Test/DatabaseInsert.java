package Test;

import Config.config;
import Functions.Database;
import java.util.ArrayList;

public class DatabaseInsert {
    
    public static void main(String[] args) {
        ArrayList<String> waardes = new ArrayList<>();
        waardes.add("123");
        waardes.add("123");
        waardes.add("123");
        waardes.add("123");
        waardes.add("10");
        waardes.add("12");
        waardes.add("TZTSDH34GJU");
        waardes.add("2");
        Database.insert(config.PAKKETINSERT, waardes);
    }

}
