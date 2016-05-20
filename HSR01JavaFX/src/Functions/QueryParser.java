package Functions;

import Config.config;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryParser {

    public static String setCategorieZoekopdracht(String query, String categorie, String zoekopdracht) {
        StringBuilder sb = new StringBuilder();
        Pattern where = Pattern.compile("ORDER");
        Matcher match = where.matcher(query);

        int endIndex = 0;
        while (match.find()) {
            endIndex = match.start();
        }

        sb.append(query.substring(0, endIndex));
        sb.append(" AND ");
        sb.append(categorie);
        sb.append(" LIKE '%");
        sb.append(zoekopdracht);
        sb.append("%' ");
        sb.append(query.substring(endIndex));

        System.out.println(sb.toString());

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(setCategorieZoekopdracht("SELECT "
                + "p.idpakket, "
                + "p.barcode, "
                + "(CASE p.locatie "
                + "WHEN 1 THEN 'Verwerking' "
                + "WHEN 2 THEN 'Opgehaald' "
                + "WHEN 3 THEN 'In de trein' "
                + "WHEN 4 THEN 'Afgeleverd' "
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
                + "ORDER BY p.idpakket;", "idpakket", "f"));
    }
}
