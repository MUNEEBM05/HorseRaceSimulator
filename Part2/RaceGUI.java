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

//The main race customisation page
//3 rows on 3 horse customisation options
//After you enter the horse name and fur type  it shows the features of that horse below
//The features include confidence rate, speed , number of wins and loses
//The other features:
//Are race options (cutomisations), track length
//Are how much you want to bet, horse you bet on
public class RaceGUI implements ActionListener
{
    private HashMap<String, Double> horseslist;
    
    String Surname;
    String name;
    int token;
    
    
    Color Gold = new Color(199, 153, 12);
    
    JFrame frame = new JFrame();
    JLabel wellcomeLabel = new JLabel();
    JLabel tokencount = new JLabel();
    JLabel horse1 = new JLabel("Horse 1");
    JLabel horse2 = new JLabel("Horse 2");
    JLabel horse3 = new JLabel("Horse 3");
    
    String [] tracks = {"None","Gold and Black", "Tron", "Classic farm"};
    JComboBox<String> trackchoice = new JComboBox<>(tracks);
    JLabel track = new JLabel("Track");
    
    Integer [] lengths = {10,20,30,40,50,60,70,80,90,100};
    JComboBox<Integer> Racelengthchoice = new JComboBox<>(lengths);
    JLabel racelength = new JLabel("Race length");
    
    Integer [] betamount = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40};
    JComboBox<Integer> BetField = new JComboBox<>(betamount);
    JLabel BetLabel= new JLabel("Bet amount");
    
    String [] horseslists = {"Horse 1","Horse 2","Horse 3"};
    JComboBox<String> HorseChoosefield = new JComboBox<>(horseslists);
    JLabel HorseChooseLabel= new JLabel("Pick Horse bet");
    
    JLabel messageLabel = new JLabel();
    
    
    JButton startRace = new JButton("Start Race");
    JButton resetButton = new JButton("Reset");
    
    JLabel NameLabel = new JLabel("Name: ");
    JLabel SymbolLabel = new JLabel("Fur choice: ");
    JLabel SpeedLabel = new JLabel("Speed: ");
    JLabel WinLabel = new JLabel("Wins:");
    JLabel LooseLabel = new JLabel("Loses:");
    
    JTextField NameField1 = new JTextField();
    JTextField NameField2 = new JTextField();
    JTextField NameField3 = new JTextField();

    String [] horsebreeds = {"Black fur","White fur"};
    JComboBox<String> SymbolField1 = new JComboBox<>(horsebreeds);
    JComboBox<String> SymbolField2 = new JComboBox<>(horsebreeds);
    JComboBox<String> SymbolField3 = new JComboBox<>(horsebreeds);
    
    JLabel speed1 = new JLabel();
    JLabel speed2 = new JLabel();
    JLabel speed3 = new JLabel();
    
    JLabel win1 = new JLabel();
    JLabel win2 = new JLabel();
    JLabel win3 = new JLabel();
    
    JLabel lose1 = new JLabel();
    JLabel lose2 = new JLabel();
    JLabel lose3 = new JLabel();
    
    JButton YESButton = new JButton("YES");
    JButton NOButton = new JButton("NO");
    JLabel Label = new JLabel("Do you wish to enter a horse?");
    
    JButton save1 = new JButton("Save");
    JButton save2 = new JButton("Save");
    JButton save3 = new JButton("Save");
    
    JLabel confidenceLabel = new JLabel("Rate:");
    
    JLabel rate1 = new JLabel();
    JLabel rate2 = new JLabel();
    JLabel rate3 = new JLabel();
    
    public RaceGUI(String userID, String SurnameID,int tokenID) 
    {
        horseslist = new HashMap<>();
        reloadUsers();
        
        rate1.setBounds(100,350,100,25);
        rate1.setFocusable(false);
        rate1.setBackground(Gold);
        rate1.setOpaque(true);
        rate1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        rate2.setBounds(230,350,100,25);
        rate2.setFocusable(false);
        rate2.setBackground(Gold);
        rate2.setOpaque(true);
        rate2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        rate3.setBounds(350,350,100,25);
        rate3.setFocusable(false);
        rate3.setBackground(Gold);
        rate3.setOpaque(true);
        rate3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        save1.setBounds(100,300,100,25);
        save1.setFocusable(false);
        save1.setBackground(Gold);
        save1.setOpaque(true);
        save1.setBorderPainted(true);
        save1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        save1.addActionListener(this);
        
        save2.setBounds(230,300,100,25);
        save2.setFocusable(false);
        save2.setBackground(Gold);
        save2.setOpaque(true);
        save2.setBorderPainted(true);
        save2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        save2.addActionListener(this);
        
        save3.setBounds(350,300,100,25);
        save3.setFocusable(false);
        save3.setBackground(Gold);
        save3.setOpaque(true);
        save3.setBorderPainted(true);
        save3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        save3.addActionListener(this);
        
        Label.setBounds(500,200,300,25);
        Label.setVisible(false);
        Label.setForeground(Gold);
        
        YESButton.setBounds(500,250,100,25);
        YESButton.setFocusable(false);
        YESButton.addActionListener(this);
        YESButton.setBackground(Color.GREEN);
        YESButton.setOpaque(true);
        YESButton.setBorderPainted(true);
        YESButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        YESButton.setVisible(false);
        
        
        NOButton.setBounds(650,250,100,25);
        NOButton.setFocusable(false);
        NOButton.addActionListener(this);
        NOButton.setBackground(Color.RED);
        NOButton.setOpaque(true);
        NOButton.setBorderPainted(true);
        NOButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        NOButton.setVisible(false);
        
        SymbolField1.setBounds(100,200,100,25);
        SymbolField1.setBackground(Gold);
        SymbolField1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        SymbolField2.setBounds(230,200,100,25);
        SymbolField2.setBackground(Gold);
        SymbolField2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        SymbolField3.setBounds(350,200,100,25);
        SymbolField3.setBackground(Gold);
        SymbolField3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        NameField1.setBounds(100,100,100,25);
        NameField1.setBackground(Gold);
        NameField1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        NameField2.setBounds(230,100,100,25);
        NameField2.setBackground(Gold);
        NameField2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        NameField3.setBounds(350,100,100,25);
        NameField3.setBackground(Gold);
        NameField3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        lose1.setBounds(100,600,100,25);
        lose1.setFocusable(false);
        lose1.setBackground(Gold);
        lose1.setOpaque(true);
        lose1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        lose2.setBounds(230,600,100,25);
        lose2.setFocusable(false);
        lose2.setBackground(Gold);
        lose2.setOpaque(true);
        lose2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        lose3.setBounds(350,600,100,25);
        lose3.setFocusable(false);
        lose3.setBackground(Gold);
        lose3.setOpaque(true);
        lose3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        
        win1.setBounds(100,500,100,25);
        win1.setFocusable(false);
        win1.setBackground(Gold);
        win1.setOpaque(true);
        win1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        win2.setBounds(230,500,100,25);
        win2.setFocusable(false);
        win2.setBackground(Gold);
        win2.setOpaque(true);
        win2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        win3.setBounds(350,500,100,25);
        win3.setFocusable(false);
        win3.setBackground(Gold);
        win3.setOpaque(true);
        win3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        confidenceLabel.setBounds(20,350,75,25);
        confidenceLabel.setForeground(Gold);
        NameLabel.setBounds(20,100,75,25);
        NameLabel.setForeground(Gold);
        SymbolLabel.setBounds(20,200,75,25);
        SymbolLabel.setForeground(Gold);
        SpeedLabel.setBounds(20,400,75,25);
        SpeedLabel.setForeground(Gold);
        WinLabel.setBounds(20,500,75,25);
        WinLabel.setForeground(Gold);
        LooseLabel.setBounds(20,600,75,25);
        LooseLabel.setForeground(Gold);
        
        speed1.setBounds(100,400,100,25);
        speed1.setFocusable(false);
        speed1.setBackground(Gold);
        speed1.setOpaque(true);
        speed1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        speed2.setBounds(230,400,100,25);
        speed2.setFocusable(false);
        speed2.setBackground(Gold);
        speed2.setOpaque(true);
        speed2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        speed3.setBounds(350,400,100,25);
        speed3.setFocusable(false);
        speed3.setBackground(Gold);
        speed3.setOpaque(true);
        speed3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        
        
        messageLabel.setBounds(500,400,500,35);
        messageLabel.setFont(new Font( "Verana",Font.BOLD,25));
        
        
        Surname = SurnameID;
        name = userID;
        token = tokenID;
        
        wellcomeLabel.setBounds(0,0,300,35);
        wellcomeLabel.setFont(new Font("Verana",Font.BOLD,25));
        wellcomeLabel.setText("Hello " + this.name + " " + this.Surname);
        wellcomeLabel.setForeground(Gold);
        
        tokencount.setBounds(0,25,300,35);
        tokencount.setFont(new Font("Verana",Font.BOLD,15));
        tokencount.setText("You currently have " + this.token + " tokens");
        tokencount.setForeground(Gold);
        
        horse1.setBounds(100,60,100,25);
        horse1.setFocusable(false);
        horse1.setBackground(Gold);
        horse1.setOpaque(true);
        horse1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        
        horse2.setBounds(230,60,100,25);
        horse2.setFocusable(false);
        horse2.setBackground(Gold);
        horse2.setOpaque(true);
        horse2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        horse3.setBounds(350,60,100,25);
        horse3.setFocusable(false);
        horse3.setBackground(Gold);
        horse3.setOpaque(true);
        horse3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        
        track.setBounds(470,60,100,25);
        track.setForeground(Color.BLACK);
        track.setFocusable(false);
        track.setBackground(Gold);
        track.setOpaque(true);
        track.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        trackchoice.setBounds(470,90,100,25);
        trackchoice.setForeground(Color.BLACK);
        trackchoice.setFocusable(false);
        trackchoice.setBackground(Gold);
        trackchoice.setOpaque(true);
        trackchoice.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        racelength.setBounds(590,60,100,25);
        racelength.setForeground(Color.BLACK);
        racelength.setFocusable(false);
        racelength.setBackground(Gold);
        racelength.setOpaque(true);
        racelength.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        Racelengthchoice.setBounds(590,90,100,25);
        Racelengthchoice.setForeground(Color.BLACK);
        Racelengthchoice.setFocusable(false);
        Racelengthchoice.setBackground(Gold);
        Racelengthchoice.setOpaque(true);
        Racelengthchoice.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        BetLabel.setBounds(710,60,100,25);
        BetLabel.setForeground(Color.BLACK);
        BetLabel.setFocusable(false);
        BetLabel.setBackground(Gold);
        BetLabel.setOpaque(true);
        BetLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        BetField.setBounds(710,90,100,25);
        BetField.setForeground(Color.BLACK);
        BetField.setBackground(Gold);
        BetField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        HorseChooseLabel.setBounds(830,60,100,25);
        HorseChooseLabel.setForeground(Color.BLACK);
        HorseChooseLabel.setFocusable(false);
        HorseChooseLabel.setBackground(Gold);
        HorseChooseLabel.setOpaque(true);
        HorseChooseLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        HorseChoosefield.setBounds(830,90,100,25);
        HorseChoosefield.setForeground(Color.BLACK);
        HorseChoosefield.setBackground(Gold);
        HorseChoosefield.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        startRace.setBounds(0,750,100,25);
        startRace.setFocusable(false);
        startRace.setBackground(Gold);
        startRace.setOpaque(true);
        startRace.setBorderPainted(true);
        startRace.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        startRace.addActionListener(this);
        
        resetButton.setBounds(150,750,100,25);
        resetButton.setFocusable(false);
        resetButton.setBackground(Gold);
        resetButton.setOpaque(true);
        resetButton.setBorderPainted(true);
        resetButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        resetButton.addActionListener(this);
        
        frame.add(confidenceLabel);
        frame.add(rate1);
        frame.add(rate2);
        frame.add(rate3);
        frame.add(save1);
        frame.add(save2);
        frame.add(save3);
        frame.add(Label);
        frame.add(YESButton);
        frame.add(NOButton);
        frame.add(SymbolField1);
        frame.add(SymbolField2);
        frame.add(SymbolField3);
        frame.add(NameField1);
        frame.add(NameField2);
        frame.add(NameField3);
        frame.add(lose1);
        frame.add(lose2);
        frame.add(lose3);
        frame.add(win1);
        frame.add(win2);
        frame.add(win3);
        frame.add(NameLabel);
        frame.add(SymbolLabel);
        frame.add(SpeedLabel);
        frame.add(WinLabel);
        frame.add(LooseLabel);
        frame.add(speed1);
        frame.add(speed2);
        frame.add(speed3);
        frame.add(HorseChooseLabel);
        frame.add(HorseChoosefield);
        frame.add(messageLabel);
        frame.add(BetLabel);
        frame.add(BetField);
        frame.add(racelength);
        frame.add(Racelengthchoice);
        frame.add(startRace);
        frame.add(trackchoice);
        frame.add(horse1);
        frame.add(horse2);
        frame.add(horse3);
        frame.add(track);
        frame.add(wellcomeLabel);
        frame.add(tokencount);
        frame.add(resetButton);
        
        addBackground(frame,"HorsePic5.jpg");
        
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,900);
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
    
    //The override methods for all the buttons
    //The buttons are save1,save2,save3 and StartRace
    //Thes buttons are to make sure the horse parameters are correct
    //All 3 save1,save2,save3 butttons do the same thing but for different Horses
    //Startrace makes sure the horse1,horse2,horse3 fields are full before showing race
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Clears all the previously entered fields
        if (e.getSource() == resetButton)
        {
            trackchoice.setSelectedIndex(0);
            Racelengthchoice.setSelectedIndex(0);
            BetField.setSelectedIndex(0);
            NameField1.setText("");
            NameField2.setText("");
            NameField3.setText("");
            SymbolField1.setSelectedIndex(0);
            SymbolField2.setSelectedIndex(0);
            SymbolField3.setSelectedIndex(0);
            lose1.setText("");
            lose2.setText("");
            lose3.setText("");
            win1.setText("");
            win2.setText("");
            win3.setText("");
            speed1.setText("");
            speed2.setText("");
            speed3.setText("");
        }
        
        //save1 is for horse1 where it checks the entered horse data
        //if the data is incorrect it asks if they wnat to enter a new horse
        //This takes you to the CreateHorse page
        if (e.getSource() == save1)
        {
            //Varibales that will be used later to create horse object
            String name = NameField1.getText();
            String symbol = getHorseSymbol(SymbolField1);
            String ratetext = "";
            String speedtext = "";
            String wintext = "";
            String losetext = "";
            if (name.isEmpty()) //Checks if the NameField1 box is empty
            {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("You must enter a name");
            }
            //Checks the hashmap of horses to see if there is a confidence rate
            //This branch is used to find the other qualities of the horse
            else if (horseslist.containsKey(name + " " + symbol))
            {
                messageLabel.setForeground(Color.green);
                messageLabel.setText("Horse 1 Login successful");
                double confidence = horseslist.get(name + " " + symbol);
                NameField1.setEditable(false);
                SymbolField1.setEnabled(false);
                //Checks the horseslist text file for the entered horse
                try (BufferedReader reader = new BufferedReader(new FileReader("horseslist.txt")))
                {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts[0].equals(name) && parts[1].equals(symbol))
                        {
                            //Finds the other details for that horse
                            //Confidence rate, speed, number of wins and loses
                            ratetext = parts[2];
                            speedtext = parts[3];
                            wintext = parts[4];
                            losetext = parts[5];
                        }
                    }
                }
                catch(IOException g)
                {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Horse 1 Login failed");
                }
                //To be more tidy to clear any unwanted buttons
                Label.setVisible(false);
                YESButton.setVisible(false);
                NOButton.setVisible(false);
                
            }
            else
            {
                //Backup message to see if parameters are not met at all
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Login failed");
                Label.setVisible(true);
                YESButton.setVisible(true);
                NOButton.setVisible(true);
            }
            
            //Gives the information that was taken form the  text file to the labels
            //The labesl cannot be edited so this shows you the horse statistics that you chose
            rate1.setText(ratetext);
            speed1.setText(speedtext);
            win1.setText(wintext);
            lose1.setText(losetext);
        }
        
        //save2 is for horse2 where it checks the entered horse data
        //if the data is incorrect it asks if they wnat to enter a new horse
        //This takes you to the CreateHorse page
        if (e.getSource() == save2)
        {
            //Varibales that will be used later to create horse object
            String name = NameField2.getText();
            String symbol = getHorseSymbol(SymbolField2);
            String ratetext = "";
            String speedtext = "";
            String wintext = "";
            String losetext = "";
            if (name.isEmpty()) //Checks if the NameField2 box is empty
            {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("You must enter a name");
            }
            //Checks the hashmap of horses to see if there is a confidence rate
            //This branch is used to find the other qualities of the hors
            else if (horseslist.containsKey(name + " " + symbol))
            {
                messageLabel.setForeground(Color.green);
                messageLabel.setText("Horse 2 Login successful");
                double confidence = horseslist.get(name + " " + symbol);
                NameField2.setEditable(false);
                SymbolField2.setEnabled(false);
                //Checks the horseslist text file for the entered horse
                try (BufferedReader reader = new BufferedReader(new FileReader("horseslist.txt")))
                {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts[0].equals(name) && parts[1].equals(symbol))
                        {
                            //Finds the other details for that horse
                            //Confidence rate, speed, number of wins and loses
                            ratetext = parts[2];
                            speedtext = parts[3];
                            wintext = parts[4];
                            losetext = parts[5];
                        }
                    }
                }
                catch(IOException g)
                {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Horse 2 Login failed");
                }
                //To be more tidy to clear any unwanted buttons
                Label.setVisible(false);
                YESButton.setVisible(false);
                NOButton.setVisible(false);
                
            }
            else
            {
                //Backup message to see if parameters are not met at all
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Login failed");
                Label.setVisible(true);
                YESButton.setVisible(true);
                NOButton.setVisible(true);
            }
            //Gives the information that was taken form the  text file to the labels
            //The labesl cannot be edited so this shows you the horse statistics that you chose
            rate2.setText(ratetext);
            speed2.setText(speedtext);
            win2.setText(wintext);
            lose2.setText(losetext);
        }
        
        //save3 is for horse3 where it checks the entered horse data
        //if the data is incorrect it asks if they wnat to enter a new horse
        //This takes you to the CreateHorse page
        if (e.getSource() == save3)
        {
            //Varibales that will be used later to create horse object
            String name = NameField3.getText();
            String symbol = getHorseSymbol(SymbolField3);
            String ratetext = "";
            String speedtext = "";
            String wintext = "";
            String losetext = "";
            if (name.isEmpty()) //Checks if the NameField3 box is empty
            {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("You must enter a name");
            }
            //Checks the hashmap of horses to see if there is a confidence rate
            //This branch is used to find the other qualities of the hors
            else if (horseslist.containsKey(name + " " + symbol))
            {
                messageLabel.setForeground(Color.green);
                messageLabel.setText("Horse 3 Login successful");
                double confidence = horseslist.get(name + " " + symbol);
                NameField3.setEditable(false);
                SymbolField3.setEnabled(false);
                //Checks the horseslist text file for the entered horse
                try (BufferedReader reader = new BufferedReader(new FileReader("horseslist.txt")))
                {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts[0].equals(name) && parts[1].equals(symbol))
                        {
                            //Finds the other details for that horse
                            //Confidence rate, speed, number of wins and loses
                            ratetext = parts[2];
                            speedtext = parts[3];
                            wintext = parts[4];
                            losetext = parts[5];
                        }
                    }
                }
                catch(IOException g)
                {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Horse 3 Login failed");
                }
                //To be more tidy to clear any unwanted buttons
                Label.setVisible(false);
                YESButton.setVisible(false);
                NOButton.setVisible(false);
                
            }
            else
            {
                //Backup message to see if parameters are not met at all
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Login failed");
                Label.setVisible(true);
                YESButton.setVisible(true);
                NOButton.setVisible(true);
            }
            //Gives the information that was taken form the  text file to the labels
            //The labesl cannot be edited so this shows you the horse statistics that you chose
            rate3.setText(ratetext);
            speed3.setText(speedtext);
            win3.setText(wintext);
            lose3.setText(losetext);
        }
        
        //The key buttons to check if you want to enter a new Horse
        //If press no then the buttons go away
        if (e.getSource() == NOButton)
        {
            Label.setVisible(false);
            YESButton.setVisible(false);
            NOButton.setVisible(false);
        }
        
        //If press yes they take you to a new page called CreateHorse
        if (e.getSource() == YESButton)
        {
            frame.dispose();
            CreateHorse maker = new CreateHorse(name, Surname, token);
        }
        
        //The main button that starts the actual race
        if (e.getSource() == startRace)
        {
            //checks if all the horse data has been entered
            if (NameField1.isEditable() || NameField2.isEditable() || NameField3.isEditable())
            {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("You need to fill all the Horse fields");
            }
            //If all the horse data has been entered it moves on
            else
            {
                //Creates the 3 horses useig the data in the JLabels
                String horsename1 = NameField1.getText();
                char [] SYMBOLS1 = getHorseSymbol(SymbolField1).toCharArray();
                char horsesymbol1 = SYMBOLS1[0];
                double horseconfidence1 = (double) Math.round(Double.parseDouble(rate1.getText())*10.0)/10.0;
                double horsespeed1 =  (double) Math.round(Double.parseDouble(speed1.getText())*10.0)/10.0;
                int horsewin1 = Integer.parseInt(win1.getText());
                int horseloss1 = Integer.parseInt(lose1.getText());
                
                String horsename2 = NameField2.getText();
                char [] SYMBOLS2 = getHorseSymbol(SymbolField2).toCharArray();
                char horsesymbol2 = SYMBOLS1[0];
                double horseconfidence2 = (double) Math.round(Double.parseDouble(rate2.getText())*10.0)/10.0;
                double horsespeed2 =  (double) Math.round(Double.parseDouble(speed2.getText())*10.0)/10.0;
                int horsewin2 = Integer.parseInt(win2.getText());
                int horseloss2 = Integer.parseInt(lose2.getText());
                
                String horsename3 = NameField1.getText();
                char [] SYMBOLS3 = getHorseSymbol(SymbolField3).toCharArray();
                char horsesymbol3 = SYMBOLS3[0];
                double horseconfidence3 = (double) Math.round(Double.parseDouble(rate3.getText())*10.0)/10.0;
                double horsespeed3 =  (double) Math.round(Double.parseDouble(speed3.getText())*10.0)/10.0;
                int horsewin3 = Integer.parseInt(win3.getText());
                int horseloss3 = Integer.parseInt(lose3.getText());
                
                //Creates the horses horse1,horse2,horse3
                Horse horse1 = new Horse(horsesymbol1,horsename1,horseconfidence1);
                horse1.setSpeed(horsespeed1);
                horse1.SetWin(horsewin1);
                horse1.SetLoss(horsewin1);
                
                Horse horse2 = new Horse(horsesymbol2,horsename2,horseconfidence2);
                horse1.setSpeed(horsespeed2);
                horse1.SetWin(horsewin2);
                horse1.SetLoss(horsewin2);
                
                Horse horse3 = new Horse(horsesymbol3,horsename3,horseconfidence3);
                horse1.setSpeed(horsespeed3);
                horse1.SetWin(horsewin3);
                horse1.SetLoss(horsewin3);
                
                //Extracts the other data such as betting money, betting horse, track length and choice
                int l = Racelengthchoice.getSelectedIndex();
                int length = lengths[0];
                int s = trackchoice.getSelectedIndex();
                String setup = tracks[s];
                int b = BetField.getSelectedIndex();
                int bet = betamount[b];
                
                //Initially sets bettig horse to null
                Horse betHorse = null;
                
                //Betting horse is set through the if statement
                if (HorseChoosefield.getSelectedIndex() == 0)
                {
                    betHorse = horse1;
                }
                else if (HorseChoosefield.getSelectedIndex() == 1)
                {
                    betHorse = horse2;
                }
                else if (HorseChoosefield.getSelectedIndex() == 2)
                {
                    betHorse = horse3;
                }
                
                //Follows on to a new class called Actualrace which sets up the race on another JFrame
                ActualRace r = new ActualRace(length, setup, horse1, horse2, horse3, betHorse, bet,token,name,Surname);
            }
        }
        
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
    
    //This is done so any newly entered accounts from CreateHorse class
    //Automatically updates the hashmap system when recalled at beginning
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

}
