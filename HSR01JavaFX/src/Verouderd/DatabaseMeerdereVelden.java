package Verouderd;

import java.sql.*;
import Config.config;
import java.util.ArrayList;

public class DatabaseMeerdereVelden {
    //Voert een query uit, zet de meegegeven velden in een ArrayList en zet deze ArrayLists vervolgens in één overkoepelende ArrayList, die wordt gereturnd
    public static ArrayList<ArrayList<String>> fetchData(String query, ArrayList<String> velden) {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        Connection conn;
        try {
            //MySQL driver aanroepen
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
                    ArrayList<String> tempArrayList = new ArrayList<>();
                    for (String i : velden) {
                        tempArrayList.add(rs.getString(i));
                    }
                    data.add(new ArrayList<>(tempArrayList));
                    tempArrayList.clear();
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
