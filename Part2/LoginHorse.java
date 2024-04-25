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

public class LoginHorse implements ActionListener
{
    Horse horse;
    
    private HashMap<String, Double> horseslist;
    
    JFrame frame = new JFrame();
    JButton SaveButton = new JButton("Save");
    JButton resetButton = new JButton("Reset");
    
    JTextField NameField = new JTextField();
    String [] horsebreeds = {"Black fur","White fur"};
    JComboBox<String> SymbolField = new JComboBox<>(horsebreeds);
    
    JLabel NameLabel = new JLabel("Name: ");
    JLabel SymbolLabel = new JLabel("Fur choice: ");
    
    JLabel messageLabel = new JLabel();
    
    JButton YESButton = new JButton("YES");
    JButton NOButton = new JButton("NO");
    JLabel Label = new JLabel("Do you wish to enter a horse?");
    
    
    LoginHorse()
    {
        horseslist = new HashMap<>();
        reloadUsers();
        
        Color Gold = new Color(199, 153, 12);
        
        NameLabel.setBounds(50,100,75,25);
        NameLabel.setForeground(Color.BLACK);
        
        SymbolLabel.setBounds(50,150,75,25);
        SymbolLabel.setForeground(Color.BLACK);
        
        
        messageLabel.setBounds(125,50,250,35);
        messageLabel.setFont(new Font( "Verana",Font.BOLD,25));

        
        NameField.setBounds(125,100,200,25);
        NameField.setBackground(Gold);
        NameField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        
        SymbolField.setBounds(125,150,200,25);
        SymbolField.setBackground(Gold);
        SymbolField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        
        SaveButton.setBounds(125,200,100,25);
        SaveButton.setFocusable(false);
        SaveButton.addActionListener(this);
        SaveButton.setBackground(Gold);
        SaveButton.setOpaque(true);
        SaveButton.setBorderPainted(true);
        SaveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
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

        
        frame.add(SymbolField);
        frame.add(NameLabel);
        frame.add(SymbolLabel);
        frame.add(messageLabel);
        frame.add(NameField);
        frame.add(SaveButton);
        frame.add(resetButton);
        frame.add(Label);
        frame.add(YESButton);
        frame.add(NOButton);
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
    
    String getHorseSymbol(JComboBox<String> comboBox)
    {
        String selected = (String) comboBox.getSelectedItem();
        String choice = "";
        if (selected.equals("Black fur"))
        {
            choice =  "?";
        }
        else if (selected.equals("White fur"))
        {
            choice = "?";
        }
        
        return choice;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == resetButton)
        {
            NameField.setText("");
            SymbolField.setSelectedIndex(0);
            
        }
        
        if (e.getSource() == SaveButton)
        {
            String name = NameField.getText();
            String symbol = getHorseSymbol(SymbolField);
            
            if (horseslist.containsKey(name + " " + symbol))
            {
                messageLabel.setForeground(Color.green);
                messageLabel.setText("Login successful");
                double confidence = horseslist.get(name + " " + symbol);
                frame.dispose();
                
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
            CreateHorse maker = new CreateHorse();
        }
    }
    
    public void reloadUsers()
    {
        // clear any existing user data
        horseslist.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader("horseslist.txt")))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String symbol = parts[1];
                double confidence = Double.parseDouble(parts[2]);
                horseslist.put(name + " " + symbol, confidence);
            }
        } 
        catch (IOException e) 
        {
            messageLabel.setForeground(Color.red);
            messageLabel.setText("Error reading horseslist.txt");
        }
    }
    
    
    Horse getHorse()
    {
        String name = NameField.getText();
        String symbol = getHorseSymbol(SymbolField);
        char symbolchar = symbol.toCharArray()[0];
        double confidence = horseslist.get(name + " " + symbol);
        horse = new Horse(symbolchar,name,confidence);
        String speed = "";
        String win = "";
        String loss = "";
        
        try (BufferedReader reader = new BufferedReader(new FileReader("horseslist.txt")))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(name) && parts[1].equals(symbol))
                {
                    speed = parts[3];
                    win = parts[4];
                    loss = parts[5];
                }
            }
        } 
        catch (IOException e) 
        {
            messageLabel.setForeground(Color.red);
            messageLabel.setText("Error reading horseslist.txt");
        }
        horse.setSpeed(Math.floor(Double.parseDouble(speed)*10.0) / 10.0);
        horse.SetWin(Integer.parseInt(win));
        horse.SetLoss(Integer.parseInt(loss));
        
        return horse;
        
    }
}
