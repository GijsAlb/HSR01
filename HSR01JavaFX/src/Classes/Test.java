package Classes;

import java.sql.*;
import Config.config;

public class Test {

    public static void main(String[] args) {
        Connection conn;
        try {
            Class.forName(config.DRIVER).newInstance();
            conn = DriverManager.getConnection(config.URL, config.USERNAME, config.PASSWORD);
            //System.out.println("[OUTPUT FROM SELECT]");
            //String query = "INSERT INTO persoon (type_id,voornaam,achternaam,postcode,huisnummer) VALUES (1,'Martijn','Hacker','0001AA','01')";
            String query = "SELECT * FROM persoon;";
            try {
                Statement st = conn.createStatement();
                //int rs = st.executeUpdate(query);
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    String a = rs.getString("voornaam");
                    String c = rs.getString("achternaam");
                    System.out.println(a + " " + c);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            conn.close();

        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.err.println(ex.getMessage());
        } catch (InstantiationException ex) {
            System.err.println(ex.getMessage());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
