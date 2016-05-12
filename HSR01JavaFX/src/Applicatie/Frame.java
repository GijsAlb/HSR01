package Applicatie;
import Classes.DatabaseConnection;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

public class Frame extends JFrame {
    //Attributes
    private DatabaseConnection dbc;
    private HashMap<Integer, HashMap<String, String>> dbHashMap;
    
    //Constructors
    public Frame(DatabaseConnection d) {
        setTitle("Pakkettenmonitor");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        dbc = d;
        dbHashMap = dbc.connect();
        
        
        setVisible(true);
    }
    
    //Listener
    public void actionPerformed(ActionEvent e) {
        
    }
}
