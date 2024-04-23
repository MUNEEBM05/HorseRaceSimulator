
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WellcomePage
{
    JFrame frame = new JFrame();
    JLabel wellcomeLabel = new JLabel("Hello");
    
    WellcomePage(String userID, int tokenID)
    {
        wellcomeLabel.setBounds(0,0,200,35);
        wellcomeLabel.setFont(new Font(null,Font.PLAIN,25));
        wellcomeLabel.setText("Hello " + userID + " you have " + tokenID);
        
        frame.add(wellcomeLabel);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
        
    }
}