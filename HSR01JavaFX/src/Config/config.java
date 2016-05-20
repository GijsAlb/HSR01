package Config;

public class config {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://michelvaartjes.nl/micheic28_tztdb";
    public static final String USERNAME = "micheic28_tzt";
    public static final String PASSWORD = "tztuserkek420";
    
    //Pakketquery's
    public static final String PAKKETKOLOMMENQUERY = "SELECT "
                                                        + "p.idpakket AS Pakketid, "
                                                        + "p.barcode AS Barcode, "
                                                        + "p.locatie AS Locatie, "
                                                        + "p.lengte as Lengte, "
                                                        + "p.breedte AS Breedte, "
                                                        + "p.hoogte AS Hoogte, "
                                                        + "p.gewicht AS Gewicht, "
                                                        + "a.idafzender AS Afzenderid, "
                                                        + "o.idontvanger AS Ontvangerid "
                                                   + "FROM pakket p "
                                                   + "JOIN afzender a "
                                                        + "ON a.idafzender = p.afzender_idafzender "
                                                   + "JOIN ontvanger o "
                                                        + "ON o.idontvanger = p.ontvanger_idontvanger;";
    public static final String PAKKETQUERY = "SELECT "
                                           + "p.idpakket AS Pakketid, "
                                           + "p.barcode AS Barcode, "
                                           + "(CASE p.locatie "
                                               + "WHEN 1 THEN 'Verwerking' "
                                               + "WHEN 2 THEN 'Opgehaald' "
                                               + "WHEN 3 THEN 'In de trein' "
                                               + "WHEN 4 THEN 'Afgeleverd' "
                                               + "ELSE 'Locatie onbekend' "
                                           + "END) AS Locatie, "
                                           + "p.lengte as Lengte, "
                                           + "p.breedte AS Breedte, "
                                           + "p.hoogte AS Hoogte, "
                                           + "p.gewicht AS Gewicht, "
                                           + "a.idafzender AS Afzenderid, "
                                           + "CONCAT(a.afzender_voornaam, ' ', a.afzender_achternaam) AS Afzender, "
                                           + "o.idontvanger AS Ontvangerid, "
                                           + "CONCAT(o.ontvanger_voornaam, ' ', o.ontvanger_achternaam) AS Ontvanger, "
                                           + "p.verwijderd AS Verwijderd "
                                       + "FROM pakket p "
                                       + "JOIN afzender a "
                                           + "ON a.idafzender = p.afzender_idafzender "
                                       + "JOIN ontvanger o "
                                           + "ON o.idontvanger = p.ontvanger_idontvanger "
                                       + "WHERE p.verwijderd = 0 "
                                       + "ORDER BY p.idpakket;";
    public static final String PAKKETDELETE = "UPDATE pakket SET verwijderd = '1' WHERE idpakket = ";
    
    //Treinkoerier query's
    public static final String TREINKOERIERKOLOMMENQUERY = "SELECT "
                                                            + "idtreinkoerier AS Treinkoerierid, "
                                                            + "CONCAT(treinkoerier_voornaam, ' ', treinkoerier_achternaam) AS Treinkoerier, "
                                                            + "treinkoerier_email AS Email, "
                                                            + "treinkoerier_postcode AS Postcode, "
                                                            + "gbdatum AS Geboortedatum, "
                                                            + "telnr AS Telefoonnummer, "
                                                            + "treinkoerier_huisnr AS Huisnummer, "
                                                            + "actief AS Actief "
                                                         + "FROM treinkoerier tk;";
    public static final String TREINKOERIERQUERY = "SELECT "
                                                    + "idtreinkoerier AS Treinkoerierid, "
                                                    + "CONCAT(treinkoerier_voornaam, ' ', treinkoerier_achternaam) AS Treinkoerier, "
                                                    + "treinkoerier_email AS Email, "
                                                    + "treinkoerier_postcode AS Postcode, "
                                                    + "gbdatum AS Geboortedatum, "
                                                    + "telnr AS Telefoonnummer, "
                                                    + "treinkoerier_huisnr AS Huisnummer, "
                                                    + "actief AS Actief "
                                                + "FROM treinkoerier tk "
                                                + "WHERE verwijderd = 0 "
                                                + "ORDER BY idtreinkoerier;";
    public static final String TREINKOERIERDELETE = "UPDATE treinkoerier SET verwijderd = '1' WHERE idtreinkoerier = ";
}