package Config;

public class config {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://michelvaartjes.nl/micheic28_tztdb";
    public static final String USERNAME = "micheic28_tzt";
    public static final String PASSWORD = "tztuserkek420";
    
    //Pakketquery's
    public static final String PAKKETQUERY = "SELECT "
                                                        + "p.idpakket, "
                                                        + "p.barcode, "
                                                        + "p.locatie, "
                                                        + "p.lengte, "
                                                        + "p.breedte, "
                                                        + "p.hoogte, "
                                                        + "p.gewicht, "
                                                        + "a.idafzender, "
                                                        + "a.afzender_voornaam, "
                                                        + "a.afzender_achternaam, "
                                                        + "o.idontvanger, "
                                                        + "o.ontvanger_voornaam, "
                                                        + "o.ontvanger_achternaam "
                                                    + "FROM pakket p "
                                                    + "JOIN afzender a "
                                                        + "ON a.idafzender = p.afzender_idafzender "
                                                    + "JOIN ontvanger o "
                                                        + "ON o.idontvanger = p.ontvanger_idontvanger "
                                                    + "WHERE p.verwijderd = 0 "
                                                    + "ORDER BY p.idpakket;";
    public static final String PAKKETINSERT = "INSERT INTO "
                                            + "pakket ("
                                            + "lengte, "
                                            + "breedte, "
                                            + "hoogte, "
                                            + "gewicht, "
                                            + "ontvanger_idontvanger, "
                                            + "afzender_idafzender, "
                                            + "barcode, "
                                            + "locatie) "
                                            + "VALUES (";
    
    //Treinkoerier query's
    public static final String TREINKOERIERQUERY = "SELECT "
                                                    + "tk.idtreinkoerier, "
                                                    + "tk.treinkoerier_voornaam, "
                                                    + "tk.treinkoerier_achternaam, "
                                                    + "tk.treinkoerier_email, "
                                                    + "tk.treinkoerier_postcode, "
                                                    + "tk.gbdatum, "
                                                    + "tk.telnr, "
                                                    + "tk.treinkoerier_huisnr, "
                                                    + "tk.actief "
                                                + "FROM treinkoerier tk "
                                                + "ORDER BY idtreinkoerier;";
    public static final String TREINKOERIERINSERT = "INSERT INTO "
                                                    + "treinkoerier ("
                                                    + "treinkoerier_voornaam, "
                                                    + "treinkoerier_achternaam, "
                                                    + "treinkoerier_email, "
                                                    + "treinkoerier_postcode, "
                                                    + "wachtwoord, "
                                                    + "gbdatum, "
                                                    + "telnr, "
                                                    + "treinkoerier_huisnr, "
                                                    + "actief) "
                                                    + "VALUES (";
}