package Config;

public class config {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://michelvaartjes.nl/micheic28_tztdb";
    public static final String USERNAME = "micheic28_tzt";
    public static final String PASSWORD = "tztuserkek420";
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
                                               + "WHEN 0 THEN 'Niet gekoppeld' "
                                               + "WHEN 1 THEN 'Opgehaald' "
                                               + "WHEN 2 THEN 'Onderweg' "
                                               + "WHEN 3 THEN 'Niet gekoppeld' "
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
}