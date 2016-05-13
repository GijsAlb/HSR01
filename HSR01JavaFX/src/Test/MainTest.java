package Test;

import Classes.DatabaseConnection;

public class MainTest {
    
    public static void main(String[] args) {
        DatabaseConnectionTest dbc = new DatabaseConnectionTest();
        FrameTest f = new FrameTest(dbc);
    }
    
}
