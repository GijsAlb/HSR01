package Functions;

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
        sb.append(" LIKE ");
        
        if(IntegerChecker.isInteger(zoekopdracht)) {
            sb.append(zoekopdracht);
        } else {
            sb.append("'%");
            sb.append(zoekopdracht);
            sb.append("%'");
        }
        
        sb.append(" ");
        sb.append(query.substring(endIndex));
        
        System.out.println(sb);
        return sb.toString();
    }
}
