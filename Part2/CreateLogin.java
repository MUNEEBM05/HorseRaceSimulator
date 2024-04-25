import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateLogin implements ActionListener
{
    
    
    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField userIDField = new JTextField();
    JTextField surnameIDField = new JTextField();
    JLabel userIDLabel = new JLabel("Name: ");
    JLabel SurnameIDLabel = new JLabel("Surname: ");
    JLabel messageLabel = new JLabel();
    JLabel Label = new JLabel("You must add your name and surname");
    
    
    CreateLogin()
    {
        Color Gold = new Color(199, 153, 12);
        
        userIDLabel.setBounds(50,100,75,25);
        SurnameIDLabel.setBounds(50,150,75,25);
        
        messageLabel.setBounds(50,300,250,35);
        messageLabel.setFont(new Font( "Verana",Font.BOLD,25));
        
        Label.setBounds(125,50,250,35);
        

        
        userIDField.setBounds(125,100,200,25);
        userIDField.setBackground(Gold);
        userIDField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        surnameIDField.setBounds(125,150,200,25);
        surnameIDField.setBackground(Gold);
        surnameIDField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        loginButton.setBounds(125,200,100,25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        loginButton.setBackground(Gold);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(true);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        resetButton.setBounds(225,200,100,25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        resetButton.setBackground(Gold);
        resetButton.setOpaque(true);
        resetButton.setBorderPainted(true);
        resetButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        frame.add(surnameIDField);
        frame.add(SurnameIDLabel);
        frame.add(userIDLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(Label);
        
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
        Image img = Toolkit.getDefaultToolkit().getImage(imagePath);
        frame.getContentPane().setLayout(new BorderLayout());
        JLabel background = new JLabel(new ImageIcon(img));
        frame.getContentPane().add(background);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == resetButton)
        {
            userIDField.setText("");
            surnameIDField.setText("");
            
        }
        
        if (e.getSource() == loginButton)
        {
            String userID = userIDField.getText();
            String SurnameID = surnameIDField.getText();
            int tokenDefault = 100;
            
            if (userID.isEmpty() || SurnameID.isEmpty())
            {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("You must enter all details");
            }
            else
            {
                try (BufferedReader reader = new BufferedReader(new FileReader("users.txt")))
                {
                    String line;
                    boolean exists = false;
                    while ((line = reader.readLine()) != null)
                    {
                        String [] parts = line.split(",");
                        if (parts[0].equals(userID) && parts[1].equals(SurnameID))
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
                        
                        try (PrintWriter p = new PrintWriter(new FileWriter("users.txt", true)))
                        {
                            p.println(userID + "," + SurnameID + "," + tokenDefault);
                        }
                        catch(IOException g)
                        {
                            messageLabel.setForeground(Color.red);
                            messageLabel.setText("error");
                        }
                
                        LoginSystem log = new LoginSystem();
                        log.reloadUsers(); // reload user data in LoginSystem
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
