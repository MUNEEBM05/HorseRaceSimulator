
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//The easiest class of the several used in the programme
//This page gives the rules and has a simple button that asks if you understands
//The button is the only way to the next page which is the selection of the race options
public class WellcomePage implements ActionListener
{
    String Surname;
    String name;
    int token;
    
    JFrame frame = new JFrame();
    JLabel wellcomeLabel = new JLabel();
    JLabel tokencount = new JLabel();
    JLabel rule1 = new JLabel();
    JLabel rule2 = new JLabel();
    JLabel rule3 = new JLabel();
    JLabel rule4 = new JLabel();
    JLabel rule5 = new JLabel();
    JLabel rule6 = new JLabel();
    JLabel rule7 = new JLabel();
    JLabel rule8 = new JLabel();
    JButton YESButton = new JButton("Understand");
    
    WellcomePage(String userID, String SurnameID,int tokenID)
    {
        Color Gold = new Color(199, 153, 12);
        Color grey = new Color(46, 41, 40);
        
        this.Surname = SurnameID;
        this.name = userID;
        this.token = tokenID;
        
        wellcomeLabel.setBounds(0,0,300,35);
        wellcomeLabel.setFont(new Font("Verana",Font.BOLD,25));
        wellcomeLabel.setText("Hello " + userID + " " + SurnameID);
        wellcomeLabel.setForeground(Gold);
        
        tokencount.setBounds(0,25,300,35);
        tokencount.setFont(new Font("Verana",Font.BOLD,15));
        tokencount.setText("You currently have " + tokenID + " tokens");
        tokencount.setForeground(Gold);
        
        rule1.setBounds(0,50,500,35);
        rule1.setFont(new Font("Verana",Font.PLAIN,20));
        rule1.setText("Here are the rules:");
        rule1.setForeground(grey);
        
        rule2.setBounds(0,70,500,35);
        rule2.setFont(new Font("Verana",Font.PLAIN,17));
        rule2.setText(" - You will select a race track");
        rule2.setForeground(grey);
        
        rule3.setBounds(0,90,500,35);
        rule3.setFont(new Font("Verana",Font.PLAIN,17));
        rule3.setText(" - Select 3 horses to choose from or add your own horse");
        rule3.setForeground(grey);
        
        rule4.setBounds(0,110,500,35);
        rule4.setFont(new Font("Verana",Font.PLAIN,17));
        rule4.setText(" - Bet on the horse you think will win with tokens");
        rule4.setForeground(grey);
        
        rule5.setBounds(0,130,500,35);
        rule5.setFont(new Font("Verana",Font.PLAIN,17));
        rule5.setText(" - You start at 100 tokens");
        rule5.setForeground(grey);
        
        rule6.setBounds(0,150,700,35);
        rule6.setFont(new Font("Verana",Font.PLAIN,17));
        rule6.setText(" - You bet how much money you want but be careful GAMBLING IS BAD");
        rule6.setForeground(grey);
        
        rule7.setBounds(0,170,500,35);
        rule7.setFont(new Font("Verana",Font.PLAIN,17));
        rule7.setText(" - If you win your bet you win the money you betted");
        rule7.setForeground(grey);
        
        rule8.setBounds(0,190,500,35);
        rule8.setFont(new Font("Verana",Font.PLAIN,17));
        rule8.setText(" - If you lose your bet you lose the money you betted");
        rule8.setForeground(grey);
        
        YESButton.setBounds(260,300,100,25);
        YESButton.setFocusable(false);
        YESButton.addActionListener(this);
        YESButton.setBackground(Gold);
        YESButton.setOpaque(true);
        YESButton.setBorderPainted(true);
        YESButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        frame.add(wellcomeLabel);
        frame.add(tokencount);
        frame.add(rule1);
        frame.add(rule2);
        frame.add(rule3);
        frame.add(rule4);
        frame.add(rule5);
        frame.add(rule6);
        frame.add(rule7);
        frame.add(rule8);
        frame.add(YESButton);
        
        addBackground(frame,"HorsePic4.jpg");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setLayout(null);
        frame.setVisible(true);
        
    }
    
    public static void addBackground(JFrame frame, String imagePath)
    {
        //Checks the JFrame and gives us the image background for it
        Image img = Toolkit.getDefaultToolkit().getImage(imagePath);
        frame.getContentPane().setLayout(new BorderLayout());
        JLabel background = new JLabel(new ImageIcon(img));
        frame.getContentPane().add(background);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //Override methods used for the button
    //As this page is a intermediate page for show we only have one button
    //This is so that the user understands the rules 
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == YESButton)
        {
            RaceGUI r = new RaceGUI(this.name,this.Surname,this.token);
        }
    }
}