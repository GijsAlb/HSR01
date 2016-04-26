package Classes;

import java.util.Date;

public class Account extends Persoon {
    //Attributes
    private String wachtwoord;
    private Date gbdatum;
    private String telnr;
    
    //Constructors
    public Account(int i, String vn, String tv, String an, int asv, String pc, String hnr, String toev, String em, String ww, String gbd, String tnr) {
        super(i, vn, tv, an, asv, pc, hnr, toev, em);
        wachtwoord = ww;
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
    
    //Methods
    
    
}