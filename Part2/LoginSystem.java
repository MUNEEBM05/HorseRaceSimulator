import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class LoginSystem implements ActionListener
{
    private HashMap<String, Integer> users;
    
    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField userIDField = new JTextField();
    JTextField surnameIDField = new JTextField();
    JLabel userIDLabel = new JLabel("Name: ");
    JLabel SurnameIDLabel = new JLabel("Surname: ");
    JLabel messageLabel = new JLabel();
    JButton YESButton = new JButton("YES");
    JButton NOButton = new JButton("NO");
    JLabel Label = new JLabel("Do you wish to join the betting?");
    
    
    LoginSystem()
    {
        users = new HashMap<>();
        reloadUsers();
        
        Color Gold = new Color(199, 153, 12);
        
        userIDLabel.setBounds(50,100,75,25);
        userIDLabel.setForeground(Color.BLACK);
        
        SurnameIDLabel.setBounds(50,150,75,25);
        SurnameIDLabel.setForeground(Color.BLACK);
        
        
        messageLabel.setBounds(125,50,250,35);
        messageLabel.setFont(new Font( "Verana",Font.BOLD,25));

        
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
        
        YESButton.setBounds(125,300,100,25);
        YESButton.setFocusable(false);
        YESButton.addActionListener(this);
        YESButton.setBackground(Gold);
        YESButton.setOpaque(true);
        YESButton.setBorderPainted(true);
        YESButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        YESButton.setVisible(false);
        
        
        NOButton.setBounds(225,300,100,25);
        NOButton.setFocusable(false);
        NOButton.addActionListener(this);
        NOButton.setBackground(Gold);
        NOButton.setOpaque(true);
        NOButton.setBorderPainted(true);
        NOButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        NOButton.setVisible(false);
        
        Label.setBounds(130,250,300,25);
        Label.setVisible(false);
        Label.setForeground(Color.BLACK);

        
        frame.add(surnameIDField);
        frame.add(SurnameIDLabel);
        frame.add(userIDLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(Label);
        frame.add(YESButton);
        frame.add(NOButton);
        frame.add(Label);
        frame.add(YESButton);
        frame.add(NOButton);
        
        addBackground(frame,"HorsePic.jpg");
        
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
            
            if (users.containsKey(userID + " " + SurnameID))
            {
                messageLabel.setForeground(Color.green);
                messageLabel.setText("Login successful");
                int tokenID = users.get(userID + " " + SurnameID);
                frame.dispose();
                WellcomePage wellcomePage = new WellcomePage(userID, SurnameID,tokenID);
            }
            else
            {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Login failed");
                Label.setVisible(true);
                YESButton.setVisible(true);
                NOButton.setVisible(true);
            }
        }    
        
        if (e.getSource() == NOButton)
        {
            Label.setVisible(false);
            YESButton.setVisible(false);
            NOButton.setVisible(false);
        }
                    
        if (e.getSource() == YESButton)
        {
            frame.dispose();
            CreateLogin maker = new CreateLogin();
        }
    }
    
    public void reloadUsers()
    {
        // clear any existing user data
        users.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt")))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String surname = parts[1];
                int tokens = Integer.parseInt(parts[2]);
                users.put(name + " " + surname, tokens);
            }
        } 
        catch (IOException e) 
        {
            messageLabel.setForeground(Color.red);
            messageLabel.setText("Error reading users.txt");
        }
    }
    
    
    
}