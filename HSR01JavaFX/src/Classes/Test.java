package Classes;

import java.sql.*;
import Config.config;

public class Test {

    public static void main(String[] args) {
        Connection conn;
        try {
            Class.forName(config.DRIVER).newInstance();
            conn = DriverManager.getConnection(config.URL, config.USERNAME, config.PASSWORD);
            
            //Inserts
            String ontvangerInsert = "INSERT INTO ontvanger (idontvanger, naam, adres, woonplaats) VALUES (1, \"Henk\", \"Hoofdstraat\", \"Hoofddorp\");";
            String afzenderInsert = "INSERT INTO afzender (idafzender, naam, adres, email, woonplaats) VALUES (1, \"Pietje\", \"Pietjestraat\", \"pietje@gmail.com\", \"Pietdorp\");";
            String pakketInsert = "INSERT INTO pakket (idpakket, lengte, breedte, hoogte, gewicht, afzender_idafzender, ontvanger_idontvanger) VALUES (1, 50, 50, 50, 500, 1, 1);";
            
            //Selects
            String ontvangerSelect = "SELECT * FROM ontvanger;";
            String pakketSelect = "SELECT * FROM pakket;";
            
            try {
                Statement st = conn.createStatement();
//                int rs = st.executeUpdate(pakketInsert);
                ResultSet rs = st.executeQuery(pakketSelect);
                while (rs.next()) {
                    //Ontvanger
//                    String a = rs.getString("naam");
//                    String b = rs.getString("adres");
//                    String c = rs.getString("woonplaats");
                    //Pakket
                    String a = rs.getString("idpakket");
                    String b = rs.getString("lengte");
                    String c = rs.getString("breedte");
                    String d = rs.getString("hoogte");
                    String e = rs.getString("gewicht");
                    
                    System.out.println(a + " " + b + " " + c + " " + d + " " + e);
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
