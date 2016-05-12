package Classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Account extends Persoon {

    //Attributes
    private String wachtwoord;
    private Date gbdatum;
    private String telnr;

    //Constructors
    public Account(int i, String vn, String tv, String an, String pc, String hnr, String toev, String em, String ww, String gbd, String tnr) {
        super(i, vn, tv, an, pc, hnr, toev, em);
        setWachtwoord(ww);
        setGbdatum(gbd);
        telnr = tnr;
    }

    //Getters
    public String getWachtwoord() {
        return wachtwoord;
    }

    public Date getGbdatum() {
        return gbdatum;
    }

    public String getTelnr() {
        return telnr;
    }

    //Setters
    public void setGbdatum(String gbd) {
        //Hier komt een methode die een MySQL Date omzet naar een Java Date
    }

    public void setTelnr(String tnr) {
        telnr = tnr;
    }

    public void setWachtwoord(String ww) {
        try {
            //MessageDigest met algoritme SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            //Wachtwoord wordt omgezet tot bytes en toegevoegd aan de MessageDigest
            md.update(ww.getBytes());
            //Hasht het (in bytes omgezette) wachtwoord en stopt die in de variabele bytes
            byte[] bytes = md.digest();
            //Bytes worden hier omgezet naar hexadecimalen
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //De hexadecimale hash wordt opgeslagen in het attribuut wachtwoord
            wachtwoord = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    
    //Methods
    
}