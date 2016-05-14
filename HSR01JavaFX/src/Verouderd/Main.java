package Verouderd;

import Verouderd.Frame;
import Functions.DatabaseConnection;

public class Main {
    
    public static void main(String[] args) {
        DatabaseConnection dbc = new DatabaseConnection();
        Frame f = new Frame(dbc);
    }
    
}