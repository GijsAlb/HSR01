package Applicatie;
import Classes.DatabaseConnection;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JFrame {
    //Attributes
    private DatabaseConnection dbc;
    
    //Constructors
    public Frame(DatabaseConnection d) {
        setTitle("Pakkettenmonitor");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        dbc = d;
        
        setVisible(true);
    }
    
    //Listener
    public void actionPerformed(ActionEvent e) {
        
    }
}
