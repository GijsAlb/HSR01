package Functions;

import java.sql.*;
import Config.config;
import java.util.ArrayList;

public class DatabaseEenVeld {
    //Attributes
    private ArrayList<String> data;

    //Methods
    public ArrayList<String> fetchData(String query, String veld) {
        data = new ArrayList<>();
        Connection conn;
        try {
            Class.forName(config.DRIVER).newInstance();
            //Connectie wordt gemaakt
            conn = DriverManager.getConnection(config.URL, config.USERNAME, config.PASSWORD);
            try {
                //Statement maken met de connectie
                Statement st = conn.createStatement();
                //Query wordt uitgevoerd en in een ResultSet gezet
                ResultSet rs = st.executeQuery(query);
                //Door de resultset heen loopen en toevoegen aan de ArrayList
                while (rs.next()) {
                    data.add(rs.getString(veld));
                }
                return data;
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
}