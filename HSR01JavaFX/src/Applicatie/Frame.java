package Applicatie;

import Classes.DatabaseConnection;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.*;

public class Frame extends JFrame {
    //Attributes
    private DatabaseConnection dbc;
    private LinkedHashMap<Integer, LinkedHashMap<String, String>> dbHashMap;
    private JLabel[][] jlabels;
    private String[] labels;
    
    //Constructors
    public Frame(DatabaseConnection d) {
        setTitle("Pakkettenmonitor");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        dbc = d;
        dbHashMap = dbc.connect();
        setLayout(new GridLayout(10,dbHashMap.size()));
        jlabels = new JLabel[dbHashMap.size()][5];
        labels = new String[5];
        
        labels[0] = "Pakket id:";
        labels[1] = "Lengte:";
        labels[2] = "Breedte:";
        labels[3] = "Hoogte:";
        labels[4] = "Gewicht:";
        
        hashMapToJLabels(dbHashMap);
        for(int a = 0; a < jlabels.length; a++) {
            for(int b = 0; b < jlabels[a].length; b++) {
                add(new JLabel(labels[b]));
                add(jlabels[a][b]);
            }
        }
        
        setVisible(true);
    }
    
    //Methods
    public void hashMapToJLabels(LinkedHashMap<Integer, LinkedHashMap<String, String>> h) {
        Iterator it1 = dbHashMap.entrySet().iterator();
        while (it1.hasNext()) {
            Map.Entry pair1 = (Map.Entry)it1.next();
            LinkedHashMap<String, String> tempHashMap = (LinkedHashMap<String, String>) pair1.getValue();
            Iterator it2 = tempHashMap.entrySet().iterator();
            int i = 0;
            while (it2.hasNext()) {
                Map.Entry pair2 = (Map.Entry) it2.next();
                jlabels[(int) pair1.getKey()][i] = new JLabel((String) pair2.getValue());
                it2.remove();
                i++;
            }
            System.out.println();
            it1.remove();
        }
    }
    
    
    //Listener
    public void actionPerformed(ActionEvent e) {
        
    }
}