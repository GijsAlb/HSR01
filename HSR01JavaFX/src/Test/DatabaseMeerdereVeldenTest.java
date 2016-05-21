package Test;

import Verouderd.DatabaseMeerdereVelden;
import java.util.ArrayList;

public class DatabaseMeerdereVeldenTest {
    
    public static void main(String[] args) {
        DatabaseMeerdereVelden dbz = new DatabaseMeerdereVelden();
        ArrayList<String> velden = new ArrayList<>();
        velden.add("gebruikersnaam");
        velden.add("wachtwoord");
        velden.add("wachtwoord(tijdelijk)");
        ArrayList<ArrayList<String>> resultaten = new ArrayList<>(dbz.fetchData("SELECT * FROM backoffice_account WHERE gebruikersnaam LIKE '%pietje%';", velden));
        int i = 0;
        for(ArrayList<String> a : resultaten) {
            System.out.print(i + " ");
            for(String s : a) {
                System.out.print(s + " ");
            }
            System.out.println();
            i++;
        }
    }
    
}
