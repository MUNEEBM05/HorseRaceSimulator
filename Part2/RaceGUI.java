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

public class RaceGUI
{
    private JTextArea raceOutput;
    
    String Surname;
    String name;
    int token;
    Horse racehorse1 = null;
    Horse racehorse2 = null;
    Horse racehorse3 = null;
    Horse betHorse = null;
    
    Color Gold = new Color(199, 153, 12);
    
    JFrame frame = new JFrame();
    JLabel wellcomeLabel = new JLabel();
    JLabel tokencount = new JLabel();
    JButton horse1 = new JButton("Horse 1");
    JButton horse2 = new JButton("Horse 2");
    JButton horse3 = new JButton("Horse 3");
    
    String [] tracks = {"None","Gold and Black", "Tron", "Classic farm"};
    JComboBox<String> trackchoice = new JComboBox<>(tracks);
    JLabel track = new JLabel("Track");
    
    Integer [] lengths = {10,20,30,40,50,60,70,80,90,100};
    JComboBox<Integer> Racelengthchoice = new JComboBox<>(lengths);
    JLabel racelength = new JLabel("Race length");
    
    Integer [] betamount = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40};
    JComboBox<Integer> BetField = new JComboBox<>(betamount);
    JLabel BetLabel= new JLabel("Bet amount");
    
    String [] horseslist = {"Horse 1","Horse 2","Horse 3"};
    JComboBox<String> HorseChoosefield = new JComboBox<>(horseslist);
    JLabel HorseChooseLabel= new JLabel("Pick Horse bet");
    
    JLabel messageLabel = new JLabel();
    
    
    JButton startRace = new JButton("Start Race");
    JButton resetButton = new JButton("Reset");
    
    JTextArea textArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(textArea);
    
    private boolean check1 = false;
    private boolean check2 = false;
    private boolean check3 = false;
    
    public RaceGUI(String userID, String SurnameID,int tokenID)
    {
        raceOutput = new JTextArea();
        
        
        messageLabel.setBounds(400,400,500,35);
        messageLabel.setFont(new Font( "Verana",Font.BOLD,25));
       
        textArea.setBounds(0,300,7,60);
        textArea.setFont(new Font("Monospaced", Font.BOLD, 15));
        textArea.setBackground(Color.WHITE);
        textArea.setForeground(Color.BLACK);
        textArea.setEditable(false);
        
        scrollPane.setPreferredSize(new Dimension(800, 600));
        RaceTrackSetUp(trackchoice, textArea);
        
        this.Surname = SurnameID;
        this.name = userID;
        this.token = tokenID;
        
        wellcomeLabel.setBounds(0,0,300,35);
        wellcomeLabel.setFont(new Font("Verana",Font.BOLD,25));
        wellcomeLabel.setText("Hello " + this.name + " " + this.Surname);
        wellcomeLabel.setForeground(Gold);
        
        tokencount.setBounds(0,25,300,35);
        tokencount.setFont(new Font("Verana",Font.BOLD,15));
        tokencount.setText("You currently have " + this.token + " tokens");
        tokencount.setForeground(Gold);
        
        horse1.setBounds(10,60,100,25);
        horse1.setFocusable(false);
        
        horse1.setBackground(Gold);
        horse1.setOpaque(true);
        horse1.setBorderPainted(true);
        horse1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        horse1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (e.getSource() == horse3)
                {
                    LoginHorse l = new LoginHorse();
                    Horse horse = l.getHorse();
                    racehorse1 = horse;
                    messageLabel.setForeground(Color.GREEN);
                    messageLabel.setText("HORSE 1 ENTERED");
                    check1 = true;
                }
            }
        });
        
        
        horse2.setBounds(130,60,100,25);
        horse2.setFocusable(false);
        horse2.setBackground(Gold);
        horse2.setOpaque(true);
        horse2.setBorderPainted(true);
        horse2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        horse2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (e.getSource() == horse2)
                {
                    LoginHorse m = new LoginHorse();
                    Horse horse = m.getHorse();
                    racehorse2 = horse;
                    messageLabel.setForeground(Color.GREEN);
                    messageLabel.setText("HORSE 2 ENTERED");
                    check2 = true;
                }
            }
        });
        
        horse3.setBounds(250,60,100,25);
        horse3.setFocusable(false);
        horse3.setBackground(Gold);
        horse3.setOpaque(true);
        horse3.setBorderPainted(true);
        horse3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        horse3.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (e.getSource() == horse3)
                {
                    LoginHorse n = new LoginHorse();
                    Horse horse = n.getHorse();
                    racehorse3 = horse;
                    messageLabel.setForeground(Color.GREEN);
                    messageLabel.setText("HORSE 3 ENTERED");
                    check3 = true;
                }
            }
        });
        
        
        track.setBounds(370,60,100,25);
        track.setForeground(Color.BLACK);
        track.setFocusable(false);
        track.setBackground(Gold);
        track.setOpaque(true);
        track.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        trackchoice.setBounds(370,90,100,25);
        trackchoice.setForeground(Color.BLACK);
        trackchoice.setFocusable(false);
        trackchoice.setBackground(Gold);
        trackchoice.setOpaque(true);
        trackchoice.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        racelength.setBounds(490,60,100,25);
        racelength.setForeground(Color.BLACK);
        racelength.setFocusable(false);
        racelength.setBackground(Gold);
        racelength.setOpaque(true);
        racelength.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        Racelengthchoice.setBounds(490,90,100,25);
        Racelengthchoice.setForeground(Color.BLACK);
        Racelengthchoice.setFocusable(false);
        Racelengthchoice.setBackground(Gold);
        Racelengthchoice.setOpaque(true);
        Racelengthchoice.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        BetLabel.setBounds(610,60,100,25);
        BetLabel.setForeground(Color.BLACK);
        BetLabel.setFocusable(false);
        BetLabel.setBackground(Gold);
        BetLabel.setOpaque(true);
        BetLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        BetField.setBounds(610,90,100,25);
        BetField.setForeground(Color.BLACK);
        BetField.setBackground(Gold);
        BetField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        HorseChooseLabel.setBounds(730,60,100,25);
        HorseChooseLabel.setForeground(Color.BLACK);
        HorseChooseLabel.setFocusable(false);
        HorseChooseLabel.setBackground(Gold);
        HorseChooseLabel.setOpaque(true);
        HorseChooseLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        HorseChoosefield.setBounds(730,90,100,25);
        HorseChoosefield.setForeground(Color.BLACK);
        HorseChoosefield.setBackground(Gold);
        HorseChoosefield.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        HorseChoosebetting(HorseChoosefield);
        
        startRace.setBounds(0,150,100,25);
        startRace.setFocusable(false);
        startRace.setBackground(Gold);
        startRace.setOpaque(true);
        startRace.setBorderPainted(true);
        startRace.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        startRace.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!check1 ||!check2 ||!check3)
                {
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Not all horses have been entered");
                }
                else
                {
                    int l = Racelengthchoice.getSelectedIndex();
                    int length = lengths[l];
                    int b = BetField.getSelectedIndex();
                    int bet = betamount[b];
                    int t = trackchoice.getSelectedIndex();
                    String setup = tracks[t];
                    frame.dispose();
                    SwingUtilities.invokeLater(()->{
                        ActualRace r = new ActualRace(length,setup, racehorse1, racehorse2, racehorse3, betHorse, bet,tokenID,userID,SurnameID);                        
                    });
                }
            }
        });
        
        resetButton.setBounds(150,150,100,25);
        resetButton.setFocusable(false);
        resetButton.setBackground(Gold);
        resetButton.setOpaque(true);
        resetButton.setBorderPainted(true);
        resetButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        resetButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                racehorse1 = null;
                racehorse2 = null;
                racehorse3 = null;
                trackchoice.setSelectedIndex(0);
                Racelengthchoice.setSelectedIndex(0);
                BetField.setSelectedIndex(0);
            }
        });
        
        frame.add(new JScrollPane(raceOutput), BorderLayout.CENTER);
        frame.add(HorseChooseLabel);
        frame.add(HorseChoosefield);
        frame.add(messageLabel);
        frame.add(BetLabel);
        frame.add(BetField);
        frame.add(racelength);
        frame.add(Racelengthchoice);
        frame.add(textArea);
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
    
    public void HorseChoosebetting(JComboBox<String> comboBox)
    {
        String selected = (String) comboBox.getSelectedItem();
        String choice = "";
        if (selected.equals("Horse 1"))
        {
            betHorse = racehorse1;
        }
        else if (selected.equals("Horse 2"))
        {
            betHorse = racehorse2;
        }
        else if (selected.equals("Horse 3"))
        {
            betHorse = racehorse3;
        }
        
    }
    
    public void RaceTrackSetUp(JComboBox<String> comboBox, JTextArea text)
    {
        String selected = (String) comboBox.getSelectedItem();
        String choice = "";
        if (selected.equals("Gold and Black"))
        {
            text.setBackground(Color.BLACK);
            text.setForeground(Gold);
        }
        else if (selected.equals("Tron"))
        {
            Color tronblue = new Color(66, 245, 245);
            text.setBackground(Color.BLACK);
            text.setForeground(tronblue);
        }
        else if (selected.equals("Classic farm"))
        {
            Color farmgreen = new Color(11, 99, 40);
            Color farmyellow = new Color(100, 148, 10);
            text.setBackground(farmgreen);
            text.setForeground(farmyellow);
        }
        else if (selected.equals("None"))
        {
            text.setBackground(Color.WHITE);
            text.setForeground(Color.BLACK);
        }
        
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
    
    
}
