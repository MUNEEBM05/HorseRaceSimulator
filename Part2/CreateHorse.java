import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//This page is only accessed when you wnat to enter a new horse
//It also makes sure no existing horses are entered
//Asks for name, fur type and confidence rate you want to set
public class CreateHorse implements ActionListener
{
    String CSurname;
    String Cname;
    int Ctoken;
    
    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    
    JTextField NameField = new JTextField();
    String [] horsebreeds = {"Black fur","White fur"};
    JComboBox<String> SymbolField = new JComboBox<>(horsebreeds);
    Double [] ratings = {0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9};
    JComboBox<Double> ConfidenceField = new JComboBox<>(ratings);
    
    JLabel NameLabel = new JLabel("Name: ");
    JLabel SymbolLabel = new JLabel("Fur choice: ");
    JLabel confidenceRateLabel = new JLabel("Confidence");
    
    JLabel messageLabel = new JLabel();
    JLabel Label = new JLabel("You must add the categories for your horse");
    
    
    CreateHorse(String userID, String SurnameID,int tokenID)
    {
        CSurname = SurnameID;
        Cname = userID;
        Ctoken = tokenID;
        
        
        Color Gold = new Color(199, 153, 12);
        
        NameLabel.setBounds(50,100,75,25);
        SymbolLabel.setBounds(50,150,75,25);
        confidenceRateLabel.setBounds(50,200,75,25);
        
        messageLabel.setBounds(125,300,250,35);
        messageLabel.setFont(new Font( "Verana",Font.BOLD,25));
        
        Label.setBounds(125,50,250,35);
        

        
        NameField.setBounds(125,100,200,25);
        NameField.setBackground(Gold);
        NameField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        
        SymbolField.setBounds(125,150,200,25);
        SymbolField.setBackground(Gold);
        SymbolField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        ConfidenceField.setBounds(125,200,200,25);
        ConfidenceField.setBackground(Gold);
        ConfidenceField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        
        loginButton.setBounds(125,250,100,25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        loginButton.setBackground(Gold);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(true);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        resetButton.setBounds(225,250,100,25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        resetButton.setBackground(Gold);
        resetButton.setOpaque(true);
        resetButton.setBorderPainted(true);
        resetButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        frame.add(SymbolField);
        frame.add(SymbolLabel);
        frame.add(NameLabel);
        frame.add(messageLabel);
        frame.add(NameField);
        frame.add(confidenceRateLabel);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(Label);
        frame.add(ConfidenceField);
        
        addBackground(frame,"HorsePic3.jpg");
        
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.getContentPane().setBackground(Gold);
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
    
    //This method returns the symbols for a black and white horse as a string
    //It is as a string but converted into a char later when sent through
    String getHorseSymbol(JComboBox<String> comboBox)
    {
        String selected = (String) comboBox.getSelectedItem();
        String choice = "";
        if (selected.equals("Black fur"))
        {
            choice =  "B";
        }
        else if (selected.equals("White fur"))
        {
            choice = "W";
        }
        
        return choice;
    }
    
    //This collects the actual chosen confidence rate the horse is started off with
    //Math.Floor is used to double check if it is to 1dp like wanted
    Double getHorseRate(JComboBox<Double> comboBox)
    {
        double selectedRate = ((Double) comboBox.getSelectedItem()).doubleValue();
        double actual = Math.floor(selectedRate *10.0) / 10.0;
        return actual;
    }
    
    //Override methods for the buttons which are reset and login
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Clears the previously typed output
        if (e.getSource() == resetButton)
        {
            NameField.setText("");
            SymbolField.setSelectedIndex(0);
            ConfidenceField.setSelectedIndex(4);
            
        }
        
        //Collects all the user data that is correct and then checks in the text file
        //Similarly to the CreateLogin login button
        if (e.getSource() == loginButton)
        {
            String name = NameField.getText();
            String symbol = getHorseSymbol(SymbolField);
            Double rate = getHorseRate(ConfidenceField);
            String rateString = String.valueOf(rate);
            
            //Makes sure the fields are not empty as empty fields are not allowed
            if (name.isEmpty() || symbol.isEmpty())
            {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("You must enter all details");
            }
            else
            {
                try (BufferedReader reader = new BufferedReader(new FileReader("horseslist.txt")))
                {
                    //This is to check that every name is unique and not already present
                    String line;
                    boolean exists = false;
                    while ((line = reader.readLine()) != null)
                    {
                        String [] parts = line.split(",");
                        if (parts[0].equals(name)) //Only checks name
                        {
                            exists = true;
                            break;
                        }
                        
                    }
                    if (exists)
                    {
                        messageLabel.setForeground(Color.red);
                        messageLabel.setText("User already exists");    
                    }
                    else
                    {
                        messageLabel.setForeground(Color.green);
                        messageLabel.setText("Login Successful");
                        
                        //Adding the new names to the system with default token count as 100
                        try (PrintWriter p = new PrintWriter(new FileWriter("horseslist.txt", true)))
                        {
                            p.println(name + "," + symbol + "," + rate + ",0,0,0");
                        }
                        catch(IOException g)
                        {
                            messageLabel.setForeground(Color.red);
                            messageLabel.setText("error");
                        }
                        
                        //Takes you back to RaceGUI for verification of name 
                        RaceGUI log = new RaceGUI(Cname, CSurname, Ctoken);
                        log.reloadUsers(); // reload user data in RaceGUI
                        frame.dispose();
                    }
                }
                catch(IOException f)
                {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("error");
                }
            }
            
            
                
        }
    }
    
    
}