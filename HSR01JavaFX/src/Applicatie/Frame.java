package Applicatie;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JFrame {
    //Attributes
    
    
    //Constructors
    public Frame() {
        setTitle("Pakkettenmonitor");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        
        
        setVisible(true);
    }
    
    //Listener
    public void actionPerformed(ActionEvent e) {
        
    }
}
