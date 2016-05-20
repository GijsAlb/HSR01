package Test;

import Config.config;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsingTest {
    public static void main(String[] args) {
        String query = config.PAKKETQUERY;
        String categorie = "idpakket";
        String zoekopdracht = "3";
        
        StringBuilder sb = new StringBuilder();
        Pattern where = Pattern.compile("ORDER");
        Matcher match = where.matcher(query);
        
        int endIndex = 0;
        while(match.find()) {
            endIndex = match.start();
            System.out.println(endIndex);
        }
        
        sb.append(query.substring(0, endIndex));
        sb.append(" AND WHERE " + categorie + " = " + zoekopdracht + " ");
        sb.append(query.substring(endIndex));
        System.out.println(sb);
        
        String returnString;
    }
}
