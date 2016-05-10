package Classes;



public class Persoon {
    //Attributes
    private int id;
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    private String postcode;
    private String huisnr;
    private String toevoeging;
    private String emailadres;
    
    //Constructors
    public Persoon (int i, String vn, String tv, String an, String pc, String hnr, String toev, String em) {
        id = i;
        voornaam = vn;
        tussenvoegsel = tv;
        achternaam = an;
        postcode = pc;
        huisnr = hnr;
        toevoeging = toev;
        emailadres = em;
    }
    
    //Getters
    public int getId() {
        return id;
    }
    public String getVoornaam() {
        return voornaam;
    }
    public String getTussenvoegsel() {
        return tussenvoegsel;
    }
    public String getAchternaam() {
        return achternaam;
    }
    public String getPostcode() {
        return postcode;
    }
    public String getHuisnr() {
        return huisnr;
    }
    public String getToevoeging() {
        return toevoeging;
    }
    public String getEmailadres() {
        return emailadres;
    }
    
    //Setters
    public void setId(int i) {
        id = i;
    }
    public void setVoornaam(String v) {
        voornaam = v;
    }
    public void setTussenvoegsel(String t) {
        tussenvoegsel = t;
    }
    public void setAchternaam(String a) {
        achternaam = a;
    }
    public void setPostcode(String p) {
        postcode = p;
    }
    public void setHuisnr(String h) {
        huisnr = h;
    }
    public void setToevoeging(String t) {
        toevoeging = t;
    }
    public void setEmailadres(String e) {
        emailadres = e;
    }
    
    //Methods
    
    
}