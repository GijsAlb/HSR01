package Classes;



public class Persoon {
    //Attributes
    private int id;
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    private int aanspreekvorm;
    private String postcode;
    private String huisnr;
    private String toevoeging;
    private String emailadres;
    
    //Constructors
    public Persoon (int i, String vn, String tv, String an, int asv, String pc, String hnr, String toev, String em) {
        id = i;
        voornaam = vn;
        tussenvoegsel = tv;
        achternaam = an;
        setAanspreekvorm(asv); //1 = Meneer, 2 = Mevrouw
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
    public int getAanspreekvorm() {
        return aanspreekvorm;
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
    public void setAanspreekvorm(int asv) {
        if(asv == 1 || asv == 2) {
            aanspreekvorm = asv;
        } else {
            System.out.println("Aanspreekvorm onbekend (niet 1 of 2)");
        }
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
    public String getAanspreekvormString() {
        if(aanspreekvorm == 1) {
            return "Meneer";
        } else if(aanspreekvorm == 2) {
            return "Mevrouw";
        } else {
            return "Onbekende aanspreekvorm";
        }
    }
    
}