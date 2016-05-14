package Functions;

import java.sql.*;
import Config.config;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class DatabaseArrayListHashMap {

    //Attributes
    private ArrayList<LinkedHashMap<String, String>> data;

    //Methods
    public ArrayList<LinkedHashMap<String, String>> fetchData(String query, String veld1, String veld2) {
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
                    Map<String, String> tempMap = new LinkedHashMap<>();
                    tempMap.put(rs.getString(veld1),rs.getString(veld2));
                    data.add(new LinkedHashMap<>(tempMap));
                    tempMap.clear();
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