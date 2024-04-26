import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

//This method shows the actual race on another JFrame with all the cutomisations we chose
//uses the newly updated Race class that was changed to show the race on a JTextArea
public class ActualRace
{
    Color Gold = new Color(199, 153, 12);
    String Surname;
    String name;
    int token;
    int LostGained;
    Race race;
    
    
    ActualRace(int length, String setup, Horse horse1, Horse horse2, Horse horse3, Horse betHorse, int bet,int current,String userID,String SurnameID)
    {
        this.token = current;
        this.LostGained = bet;
        this.Surname = SurnameID;
        this.name = userID;
        
        
        //The main actual part that showcases the race on the JFrame
        //We use Swingutilities to account for the Thread before
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JTextArea textArea = new JTextArea(7, 60);
            textArea.setFont(new Font("Monospaced", Font.BOLD, 15));
            
            //The track customisation option being used
            RaceTrackSetUp(setup,textArea);
            
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(800, 600));
            
            frame.setAlwaysOnTop(true);
            frame.add(scrollPane);
            
            frame.add(textArea);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            this.race = new Race(length, textArea);
            
            race.addHorse(horse1, 1);
            race.addHorse(horse2, 2);
            race.addHorse(horse3, 3);
            race.startRace();
            
            
        });
        
        
        this.token = UpdateToken(horse1, horse2, horse3, betHorse, bet,current);
        //updates the files for the users money
        UpdateFile(this.name, this.Surname, this.token);
        
        //A small timer before moving back to the RaceGUI class which is the cutomiser
        try
        {
            Thread.sleep(1000); //pause for 1 second
        } 
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
        
        RaceGUI r = new RaceGUI(this.name, this.Surname, this.token);
        
        
        
    }
    
    
    
    //Returns the new number of tokens remaining
    //uses the amount betted on by the user
    //If the user won they gain the money and lose the money if lost
    int UpdateToken(Horse horse1, Horse horse2, Horse horse3, Horse betHorse, int bet,int current)
    {
        Horse [] checker = {horse1, horse2, horse3};
        ArrayList<Horse> winners = new ArrayList<>();
        for (Horse horse: checker)
        {
            if (horse.didWin())
            {
                winners.add(horse);
            }
        }
        
        if (winners.contains(betHorse))
        {
            current += bet;
        }
        else
        {
            current -= bet;
        }
        
        return current;
    }
    
    //Now goes to the users.txt file and updates the users details
    //This is os that if the program is run again the details are saved
    public void UpdateFile(String userID, String Surname, int token)
    {
        
        try (BufferedReader br = new BufferedReader(new FileReader("horseslist.txt"))) {
            String line;
            StringBuilder sb = new StringBuilder();
            boolean lineUpdated = false;
            while ((line = br.readLine())!= null)
            {
                String[] parts = line.split(",");
                if (parts[0].equals(userID) && parts[1].equals(Surname))
                {
                    parts[2] = String.valueOf(token);
                    
                    lineUpdated = true;
                }
                sb.append(String.join(",", parts)).append("\n");
            }

            if (lineUpdated) {
                try (FileWriter fw = new FileWriter("horseslist.txt"))
                {
                    fw.write(sb.toString());
                } 
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    //The options for the track customisation
    //This specifically alters the JTextAre to the colourscheme chosen by us
    public void RaceTrackSetUp(String selected, JTextArea text)
    {
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
    
    
}
