import Applicatie.Frame;
import Classes.DatabaseConnection;

public class Main {
    
    public static void main(String[] args) {
        DatabaseConnection dbc = new DatabaseConnection();
        Frame f = new Frame(dbc);
    }
    
}