package Config;

public class config {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://michelvaartjes.nl/micheic28_tztdb";
    public static final String USERNAME = "micheic28_tzt";
    public static final String PASSWORD = "tztuserkek420";
    
    //Pakketquery's
    public static final String PAKKETKOLOMMENQUERY = "SELECT "
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
                                                        + "ON o.idontvanger = p.ontvanger_idontvanger;";
    
    public static final String PAKKETQUERY = "SELECT "
                                                + "p.idpakket, "
                                                + "p.barcode, "
                                                + "(CASE p.locatie "
                                                    + "WHEN 1 THEN 'Verwerking (1)' "
                                                    + "WHEN 2 THEN 'Opgehaald (2)' "
                                                    + "WHEN 3 THEN 'In de trein (3)' "
                                                    + "WHEN 4 THEN 'Afgeleverd (4)' "
                                                    + "ELSE 'Locatie onbekend' "
                                                + "END) AS locatie, "
                                                + "p.lengte, "
                                                + "p.breedte, "
                                                + "p.hoogte, "
                                                + "p.gewicht, "
                                                + "a.idafzender, "
                                                + "CONCAT(a.afzender_voornaam, ' ', a.afzender_achternaam) AS afzender, "
                                                + "o.idontvanger, "
                                                + "CONCAT(o.ontvanger_voornaam, ' ', o.ontvanger_achternaam) AS ontvanger, "
                                                + "p.verwijderd "
                                            + "FROM pakket p "
                                            + "JOIN afzender a "
                                                + "ON a.idafzender = p.afzender_idafzender "
                                            + "JOIN ontvanger o "
                                                + "ON o.idontvanger = p.ontvanger_idontvanger "
                                            + "WHERE p.verwijderd = 0 "
                                            + "ORDER BY p.idpakket;";
    
    //Treinkoerier query's
    public static final String TREINKOERIERKOLOMMENQUERY = "SELECT "
                                                            + "idtreinkoerier, "
                                                            + "treinkoerier_voornaam, "
                                                            + "treinkoerier_achternaam, "
                                                            + "treinkoerier_email, "
                                                            + "treinkoerier_postcode, "
                                                            + "gbdatum, "
                                                            + "telnr, "
                                                            + "treinkoerier_huisnr, "
                                                            + "actief "
                                                         + "FROM treinkoerier tk;";
    public static final String TREINKOERIERQUERY = "SELECT "
                                                    + "idtreinkoerier, "
                                                    + "CONCAT(treinkoerier_voornaam, ' ', treinkoerier_achternaam) AS naam, "
                                                    + "treinkoerier_email, "
                                                    + "treinkoerier_postcode, "
                                                    + "gbdatum, "
                                                    + "telnr, "
                                                    + "treinkoerier_huisnr, "
                                                    + "actief "
                                                + "FROM treinkoerier tk "
                                                + "WHERE verwijderd = 0 "
                                                + "ORDER BY idtreinkoerier;";
}