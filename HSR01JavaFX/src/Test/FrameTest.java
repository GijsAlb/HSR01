package Test;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.*;

public class FrameTest extends JFrame {
    //Attributes
    private DatabaseConnectionTest dbc;
    private LinkedHashMap<Integer, LinkedHashMap<String, String>> dbHashMap;
    private JLabel[][] jlabels;
    
    //Constructors
    public FrameTest(DatabaseConnectionTest d) {
        setTitle("Pakkettenmonitor");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10,2));
        
        dbc = d;
        dbHashMap = dbc.connect();
        jlabels = new JLabel[dbHashMap.size()][5];
        hashMapToJLabels(dbHashMap);
        for(int a = 0; a < jlabels.length; a++) {
            for(int b = 0; b < jlabels[a].length; b++) {
                add(new JLabel("label"));
                add(jlabels[a][b]);
            }
        }
        
        setVisible(true);
    }
    
    //Methods
    public void hashMapToJLabels(HashMap h) {
        Iterator it1 = dbHashMap.entrySet().iterator();
        while (it1.hasNext()) {
            Map.Entry pair1 = (Map.Entry)it1.next();
            HashMap tempHashMap = (HashMap) pair1.getValue();
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