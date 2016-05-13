package Classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reisplan {
    //Attributes
    private int idReisplan;
    private String beginstation;
    private String eindstation;
    private Date vertrektijd;
    
    //Constructors
    public Reisplan(int id, String bs, String es) {
        idReisplan = id;
        beginstation = bs;
        eindstation = es;
        //Ff nadenken hoe de vertrektijd in de java applicatie binnenkomt
    }
    
    //Getters
    public int getIdReisplan() {
        return idReisplan;
    }
    public String getBeginstation() {
        return beginstation;
    }
    public String getEindstation() {
        return eindstation;
    }
    public Date getVertrektijd() {
        return vertrektijd;
    }
    
    //Setters
    public void setIdReisplan(int idReisplan) {
        this.idReisplan = idReisplan;
    }
    public void setBeginstation(String beginstation) {
        this.beginstation = beginstation;
    }
    public void setEindstation(String eindstation) {
        this.eindstation = eindstation;
    }
    public void setVertrektijd(Date vertrektijd) {
        this.vertrektijd = vertrektijd;
    }

    //Methods
    public String getTijd() {
        SimpleDateFormat tijdFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        return tijdFormat.format(vertrektijd);
    }
    public String getDatum() {
        SimpleDateFormat datumFormat = new SimpleDateFormat("yyyy-MM-dd");
        return datumFormat.format(vertrektijd);
    }
    
}
