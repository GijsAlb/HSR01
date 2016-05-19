package Config;

public class config {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://michelvaartjes.nl/micheic28_tztdb";
    public static final String USERNAME = "micheic28_tzt";
    public static final String PASSWORD = "tztuserkek420";
    public static final String ICON = "file:src/Images/icon.png";
    public static final String PAKKETKOLOMMENQUERY = "SELECT "
                                                    + "idpakket AS Pakketid, "
                                                    + "barcode AS Barcode, "
                                                    + "locatie AS Locatie, "
                                                    + "lengte as Lengte, "
                                                    + "breedte AS Breedte, "
                                                    + "hoogte AS Hoogte, "
                                                    + "gewicht AS Gewicht "
                                                    + "FROM pakket;";
    public static final String PAKKETQUERY = "SELECT "
                                            + "idpakket AS Pakketid, "
                                            + "barcode AS Barcode, "
                                            + "(CASE locatie "
                                                + "WHEN 0 THEN 'Niet gekoppeld' "
                                                + "WHEN 1 THEN 'Opgehaald' "
                                                + "WHEN 2 THEN 'Onderweg' "
                                                + "WHEN 3 THEN 'Niet gekoppeld' "
                                                + "WHEN 4 THEN 'Afgeleverd' "
                                                + "ELSE 'Locatie onbekend' "
                                            + "END) AS Locatie, "
                                            + "lengte as Lengte, "
                                            + "breedte AS Breedte, "
                                            + "hoogte AS Hoogte, "
                                            + "gewicht AS Gewicht "
                                            + "FROM pakket;";
    
//    public static final String CSS = "file:src/CSS/JMetroDarkTheme.css";
    public static final String CSS = "file:src/CSS/JMetroLightTheme.css";
//    public static final String CSS = "file:src/CSS/stylesheet.css";
}