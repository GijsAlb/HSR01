package Classes;

public class Pakket {
    //Attributes
    private int idPakket;
    private Treinkoerier treinkoerier;
    private Afzender afzender;
    private Ontvanger ontvanger;
    private double gewicht; //In kilo's
    private int lengte; //In centimeters
    private int breedte; //In centimeters
    private int hoogte; //In centimeters
    private String huidigeLocatie;
    
    //Constructors
    public Pakket(int id, Treinkoerier tk, Afzender a, Ontvanger o, double g, int l, int b, int h, String hl) {
        idPakket = id;
        treinkoerier = tk;
        afzender = a;
        ontvanger = o;
        gewicht = g;
        lengte = l;
        breedte = b;
        hoogte = h;
        huidigeLocatie = hl;
    }
    
    //Getters
    public int getIdPakket() {
        return idPakket;
    }
    public Treinkoerier getTreinkoerier() {
        return treinkoerier;
    }
    public Afzender getAfzender() {
        return afzender;
    }
    public Ontvanger getOntvanger() {
        return ontvanger;
    }
    public double getGewicht() {
        return gewicht;
    }
    public int getLengte() {
        return lengte;
    }
    public int getBreedte() {
        return breedte;
    }
    public int getHoogte() {
        return hoogte;
    }
    public String getHuidigeLocatie() {
        return huidigeLocatie;
    }
    
    //Setters
    public void setIdPakket(int idPakket) {
        this.idPakket = idPakket;
    }
    public void setTreinkoerier(Treinkoerier treinkoerier) {
        this.treinkoerier = treinkoerier;
    }
    public void setAfzender(Afzender afzender) {
        this.afzender = afzender;
    }
    public void setOntvanger(Ontvanger ontvanger) {
        this.ontvanger = ontvanger;
    }
    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }
    public void setLengte(int lengte) {
        this.lengte = lengte;
    }
    public void setBreedte(int breedte) {
        this.breedte = breedte;
    }
    public void setHoogte(int hoogte) {
        this.hoogte = hoogte;
    }
    public void setHuidigeLocatie(String huidigeLocatie) {
        this.huidigeLocatie = huidigeLocatie;
    }
    
    //Methods
    
    
}
