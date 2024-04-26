import java.util.concurrent.TimeUnit;
import java.lang.Math;
<<<<<<< HEAD

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McFarewell
 * @version 1.0
 */
=======
import java.time.Instant;
import java.time.Duration;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import java.awt.Color;

//New changes to Part2
//Since we want the horse to be in the main interface and not the terminal
//We use a JTextField
//The JTextFiels is also a instance variable used in the race
//Major changes is we use display.append rather than System.out.println
>>>>>>> gui-development
public class Race
{
    private int raceLength;
    private Horse lane1Horse;
    private Horse lane2Horse;
    private Horse lane3Horse;
<<<<<<< HEAD

    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     */
    public Race(int distance)
=======
    private JTextArea display;

    //declare a local variable to tell us when the race is finished
    boolean finished = false;
    
    

    public Race(int distance, JTextArea display)
>>>>>>> gui-development
    {
        // initialise instance variables
        raceLength = distance;
        lane1Horse = null;
        lane2Horse = null;
        lane3Horse = null;
<<<<<<< HEAD
    }
    
    /**
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
=======
        this.display = display;
    }

    //Method has not changed
>>>>>>> gui-development
    public void addHorse(Horse theHorse, int laneNumber)
    {
        if (laneNumber == 1)
        {
            lane1Horse = theHorse;
        }
        else if (laneNumber == 2)
        {
            lane2Horse = theHorse;
        }
        else if (laneNumber == 3)
        {
            lane3Horse = theHorse;
        }
        else
        {
<<<<<<< HEAD
            System.out.println("Cannot add horse to lane " + laneNumber + " because there is no such lane");
        }
    }
    
    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the 
     * race is finished
     */
    public void startRace()
    {
        //declare a local variable to tell us when the race is finished
        boolean finished = false;
        
        //reset all the lanes (all horses not fallen and back to 0). 
        lane1Horse.goBackToStart();
        lane2Horse.goBackToStart();
        lane3Horse.goBackToStart();
                      
        while (!finished)
        {
            //move each horse
            moveHorse(lane1Horse);
            moveHorse(lane2Horse);
            moveHorse(lane3Horse);
                        
            //print the race positions
            printRace();
            
            //if any of the three horses has won the race is finished
            
            if ( raceWonBy(lane1Horse) || raceWonBy(lane2Horse) || raceWonBy(lane3Horse) )
            {
                finished = true;
                if (raceWonBy(lane1Horse))
                {
                    if (raceWonBy(lane2Horse))
                    {
                        if (raceWonBy(lane3Horse))
                        {
                            System.out.println("And the winner is: " + lane1Horse.getName() + " and " + lane2Horse.getName() + " and " + lane3Horse.getName());
                        }
                        else
                        {
                            System.out.println("And the winner is: " + lane1Horse.getName() + " and " + lane2Horse.getName());
                        }
                    }
                    else if (raceWonBy(lane3Horse))
                    {
                        if (raceWonBy(lane2Horse))
                        {
                            System.out.println("And the winner is: " + lane1Horse.getName() + " and " + lane2Horse.getName() + " and " + lane3Horse.getName());
                        }
                        else
                        {
                            System.out.println("And the winner is: " + lane1Horse.getName() + " and " + lane3Horse.getName());
                        }
                    }
                    else
                    {
                        System.out.println("And the winner is: " + lane1Horse.getName());
                    }
                }
                else if (raceWonBy(lane2Horse))
                {
                    if (raceWonBy(lane1Horse))
                    {
                        if (raceWonBy(lane3Horse))
                        {
                            System.out.println("And the winner is: " + lane1Horse.getName() + " and " + lane2Horse.getName() + " and " + lane3Horse.getName());
                        }
                        else
                        {
                            System.out.println("And the winner is: " + lane1Horse.getName() + " and " + lane2Horse.getName());
                        }
                    }
                    else if (raceWonBy(lane3Horse))
                    {
                        if (raceWonBy(lane1Horse))
                        {
                            System.out.println("And the winner is: " + lane1Horse.getName() + " and " + lane2Horse.getName() + " and " + lane3Horse.getName());
                        }
                        else
                        {
                            System.out.println("And the winner is: " + lane2Horse.getName() + " and " + lane3Horse.getName());
                        }
                    }
                    else
                    {
                        System.out.println("And the winner is: " + lane2Horse.getName());
                    }
                }
                else if (raceWonBy(lane3Horse))
                {
=======
            display.append("Cannot add horse to lane " + laneNumber + " because there is no such lane\n");
        }
    }

    public synchronized void startRace()
    {
        

        //reset all the lanes (all horses not fallen and back to 0).
        lane1Horse.goBackToStart();
        lane2Horse.goBackToStart();
        lane3Horse.goBackToStart();
        Instant start = Instant.now();
        
        //Used a Thread so we can see the horses progressin in the race
        //Otheriwse we only instantly saw the end of the race
        //Matches how the race looked on the terminal
        new Thread(() -> {
            while (!finished)
            {
                //move each horse
                moveHorse(lane1Horse);
                moveHorse(lane2Horse);
                moveHorse(lane3Horse);
    
                //print the race positions
                printRace();
    
                //if any of the three horses has won the race is finished
                if ( raceWonBy(lane1Horse) || raceWonBy(lane2Horse) || raceWonBy(lane3Horse) )
                {
                    finished = true;
>>>>>>> gui-development
                    if (raceWonBy(lane1Horse))
                    {
                        if (raceWonBy(lane2Horse))
                        {
<<<<<<< HEAD
                            System.out.println("And the winner is: " + lane1Horse.getName() + " and " + lane2Horse.getName() + " and " + lane3Horse.getName());
                        }
                        else
                        {
                            System.out.println("And the winner is: " + lane1Horse.getName() + " and " + lane3Horse.getName());
=======
                            if (raceWonBy(lane3Horse))
                            {
                                display.append("And the winner is: " + lane1Horse.getName() + " and " + lane2Horse.getName() + " and " + lane3Horse.getName() + "\n");
                            }
                            else
                            {
                                //We used GainLoss here now as a extra to see the conditon of each horse at that race
                                //This checks if the horse won or lost
                                display.append("And the winner is: " + lane1Horse.getName() + " and " + lane2Horse.getName() + "\n");
                                lane3Horse.GainLoss();
                            }
                        }
                        else if (raceWonBy(lane3Horse))
                        {
                            if (raceWonBy(lane2Horse))
                            {
                                display.append("And the winner is: " + lane1Horse.getName() + " and " + lane2Horse.getName() + " and " + lane3Horse.getName() + "\n");
                            }
                            else
                            {
                                display.append("And the winner is: " + lane1Horse.getName() + " and " + lane3Horse.getName() + "\n");
                                lane2Horse.GainLoss();
                            }
                        }
                        else
                        {
                            display.append("And the winner is: " + lane1Horse.getName() + "\n");
                            lane2Horse.GainLoss();
                            lane3Horse.GainLoss();
>>>>>>> gui-development
                        }
                    }
                    else if (raceWonBy(lane2Horse))
                    {
                        if (raceWonBy(lane1Horse))
                        {
<<<<<<< HEAD
                            System.out.println("And the winner is: " + lane1Horse.getName() + " and " + lane2Horse.getName() + " and " + lane3Horse.getName());
                        }
                        else
                        {
                            System.out.println("And the winner is: " + lane2Horse.getName() + " and " + lane3Horse.getName());
=======
                            if (raceWonBy(lane3Horse))
                            {
                                display.append("And the winner is: " + lane1Horse.getName() + " and " + lane2Horse.getName() + " and " + lane3Horse.getName() + "\n");
                            }
                            else
                            {
                                display.append("And the winner is: " + lane1Horse.getName() + " and " + lane2Horse.getName() + "\n");
                                lane3Horse.GainLoss();
                            }
                        }
                        else if (raceWonBy(lane3Horse))
                        {
                            if (raceWonBy(lane1Horse))
                            {
                                display.append("And the winner is: " + lane1Horse.getName() + " and " + lane2Horse.getName() + " and " + lane3Horse.getName() + "\n");
                            }
                            else
                            {
                                display.append("And the winner is: " + lane2Horse.getName() +" and " + lane3Horse.getName() + "\n");
                                lane1Horse.GainLoss();
                            }
                        }
                        else
                        {
                            display.append("And the winner is: " + lane2Horse.getName() + "\n");
                            lane1Horse.GainLoss();
                            lane3Horse.GainLoss();
                        }
                    }
                    else if (raceWonBy(lane3Horse))
                    {
                        if (raceWonBy(lane1Horse))
                        {
                            if (raceWonBy(lane2Horse))
                            {
                                display.append("And the winner is: " + lane1Horse.getName() + " and " + lane2Horse.getName() + " and " + lane3Horse.getName() + "\n");
                            }
                            else
                            {
                                display.append("And the winner is: " + lane1Horse.getName() + " and " + lane3Horse.getName() + "\n");
                                lane2Horse.GainLoss();
                            }
                        }
                        else if (raceWonBy(lane2Horse))
                        {
                            if (raceWonBy(lane1Horse))
                            {
                                display.append("And the winner is: " + lane1Horse.getName() + " and " + lane2Horse.getName() + " and " + lane3Horse.getName() + "\n");
                            }
                            else
                            {
                                display.append("And the winner is: " + lane2Horse.getName() + " and " + lane3Horse.getName() + "\n");
                                lane1Horse.GainLoss();
                            }
                        }
                        else
                        {
                            display.append("And the winner is: " + lane3Horse.getName() + "\n");
                            lane1Horse.GainLoss();
                            lane2Horse.GainLoss();
>>>>>>> gui-development
                        }
                    }
                    else
                    {
<<<<<<< HEAD
                        System.out.println("And the winner is: " + lane3Horse.getName());
                    }
                }
                else
                {
                    System.out.println("And the winner is: " + lane1Horse.getName() + " and " + lane2Horse.getName() + " and " + lane3Horse.getName());
                }
            }
            else if ( lane1Horse.hasFallen() &&  lane2Horse.hasFallen() && lane3Horse.hasFallen())
            {
                finished = true;
                System.out.println("All 3 horses have fallen : No winner");
            }
           
            //wait for 100 milliseconds
            try{ 
                TimeUnit.MILLISECONDS.sleep(100);
            }catch(Exception e){}
        }
    }
    
    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */
=======
                        display.append("And the winner is: " + lane1Horse.getName() + " and " + lane2Horse.getName() + " and " + lane3Horse.getName() + "\n");
                    }
                }
                else if ( lane1Horse.hasFallen() &&  lane2Horse.hasFallen() && lane3Horse.hasFallen())
                {
                    finished = true;
                    display.append("All 3 horses have fallen : No winner\n");
                    lane2Horse.GainLoss();
                    lane3Horse.GainLoss();
                    lane1Horse.GainLoss();
                }
    
                //wait for 100 milliseconds
                try{ 
                    TimeUnit.MILLISECONDS.sleep(10);
                }catch(Exception e){}
            }
        }).start();
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        double time = (double) timeElapsed.toSeconds();

        SpeedCalc(lane1Horse, lane1Horse.getDistanceTravelled(), time);
        SpeedCalc(lane2Horse, lane2Horse.getDistanceTravelled(), time);
        SpeedCalc(lane3Horse, lane3Horse.getDistanceTravelled(), time);
        
        //Newly added mmethod for updating the info of horses in horseslist.txt
        UpdateFile(lane1Horse);
        UpdateFile(lane2Horse);
        UpdateFile(lane3Horse);
    }

>>>>>>> gui-development
    private void moveHorse(Horse theHorse)
    {
        //if the horse has fallen it cannot move, 
        //so only run if it has not fallen
<<<<<<< HEAD
        
=======
>>>>>>> gui-development
        if  (!theHorse.hasFallen())
        {
            //the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence())
            {
<<<<<<< HEAD
               theHorse.moveForward();
            }
            
=======
                theHorse.moveForward();
            }

>>>>>>> gui-development
            //the probability that the horse will fall is very small (max is 0.1)
            //but will also will depends exponentially on confidence 
            //so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.1*theHorse.getConfidence()*theHorse.getConfidence()))
            {
                if (!raceWonBy(theHorse))
                {
                    double new_confidence2 = Math.floor((theHorse.getConfidence()-0.1)*100)/100;
                    theHorse.setConfidence(new_confidence2);
                    theHorse.fall();
                }
            }
        }
<<<<<<< HEAD
        
    }
        
    /** 
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonBy(Horse theHorse)
    {
        if ((theHorse.getDistanceTravelled() == raceLength && theHorse.hasFallen() == true) || (theHorse.getDistanceTravelled() == raceLength))
        {
            
=======
    }
    
    public boolean raceWonBy(Horse theHorse)
    {
        if ((theHorse.getDistanceTravelled() == raceLength && theHorse.hasFallen() == true) || (theHorse.getDistanceTravelled() == raceLength))
        {
            theHorse.GainWin();
>>>>>>> gui-development
            return true;
        }
        else
        {
            return false;
        }
    }
    
<<<<<<< HEAD
    /***
     * Print the race on the terminal
     */
    private void printRace()
    {
        System.out.print('\u000C');  //clear the terminal window
        
        multiplePrint('=',raceLength+3); //top edge of track
        System.out.println();
        
        printLane(lane1Horse);
        System.out.println();
        
        printLane(lane2Horse);
        System.out.println();
        
        printLane(lane3Horse);
        System.out.println();
        
        multiplePrint('=',raceLength+3); //bottom edge of track
        System.out.println();    
    }
    
    /**
     * print a horse's lane during the race
     * for example
     * |           X                      |
     * to show how far the horse has run
     */
    private void printLane(Horse theHorse)
    {
        //calculate how many spaces are needed before
        //and after the horse
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();
        
        //print a | for the beginning of the lane
        System.out.print('|');
        

        //print the spaces before the horse
        multiplePrint(' ',spacesBefore);
        
        //if the horse has fallen then print dead
        //else print the horse's symbol
        if(theHorse.hasFallen())
        {
            System.out.print('\u274C');
        }
=======
    //instead of System we use diplay (name of JTextArea)
    //This is so that the track is not in the terminal but in the JTextArea
    private void printRace()
    {
        //clear the JTextArea
        display.setText("");

        multiplePrint("=",raceLength+3); //top edge of track
        display.append("\n");

        printLane(lane1Horse);
        display.append("\n");

        printLane(lane2Horse);
        display.append("\n");

        printLane(lane3Horse);
        display.append("\n");

        multiplePrint("=",raceLength+3); //bottom edge of track
        display.append("\n");
        
        try
        {
            Thread.sleep(10); //pause for 1 second
        } 
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    //Same appication of the diplay rather than System
    private void printLane(Horse theHorse)
    {
        // calculate the number of spaces before and after the horse
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();
    
        // print the left edge of the lane
        
        display.append("|");
    
        // print the spaces before the horse
        for (int i = 0; i < spacesBefore; i++) {
            display.append(" ");
        }
    
        // print the horse's symbol or "F" if the horse has fallen
        if (theHorse.hasFallen()) 
        {
            display.append("\u274C");
        } 
>>>>>>> gui-development
        else if (raceWonBy(theHorse))
        {
            double new_confidence1 = Math.floor((theHorse.getConfidence()+0.1)*100)/100;
            theHorse.setConfidence(new_confidence1);
<<<<<<< HEAD
            System.out.print(theHorse.getSymbol());
        }
        else
        {
            System.out.print(theHorse.getSymbol());
        }
        
        //print the spaces after the horse
        multiplePrint(' ',spacesAfter);
        
        //print the | for the end of the track
        System.out.print('|' + theHorse.getName() + " (Current confidence " + theHorse.getConfidence() + ")");
    }
        
    
    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     * 
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
=======
            display.append(""+theHorse.getSymbol());
        }
        else
        {
            
            display.append(""+theHorse.getSymbol());
        }
    
        // print the spaces after the horse
        for (int i = 0; i < spacesAfter; i++) {
            display.append(" ");
        }
    
        // print the right edge of the lane
        display.append("| "+ theHorse.getName() + " (Current confidence " + theHorse.getConfidence() + ")");
    }

    private void SpeedCalc(Horse theHorse, int distance, double time)
    {
        double newSpeed = Math.floor((distance / time)* 10.0) / 10.0;
        theHorse.setSpeed(newSpeed);
    }
    
    //new method which is for updating the new information on the horses stats
    //since the horse might have lost or gained confidence it needs to be updated
    //The horse would definately have gained a win or loss so this method is needed
    public void UpdateFile(Horse theHorse)
    {
        //Assigns variabl
        String targetName = theHorse.getName();
        String targetSymbol = String.valueOf(theHorse.getOGSymbol());
        String newConfidenceRate = String.valueOf(theHorse.getConfidence());
        String newSpeed = String.valueOf(theHorse.getSpeed());
        String newWin = String.valueOf(theHorse.getWin());
        String newLoss = String.valueOf(theHorse.getLoss());

        try (BufferedReader br = new BufferedReader(new FileReader("horseslist.txt"))) {
            String line;
            StringBuilder sb = new StringBuilder();
            boolean lineUpdated = false;
            while ((line = br.readLine())!= null)
            {
                String[] parts = line.split(",");
                if (Double.parseDouble(parts[2]) == 0)//if confidence rate is 0
                {
                    //This completely removed the horse data
                    //This because with a 0 rate it falls instantly
                    //The horse is considered dead
                    sb.append(line).append("\n");
                }
                else if (parts[0].equals(targetName) && parts[1].equals(targetSymbol))
                {
                    //Updates the new statistics of the horse
                    parts[2] = newConfidenceRate;
                    parts[3] = newSpeed;
                    parts[4] = newWin;
                    parts[5] = newLoss;
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
    
    //Instead of useing char for multiprint we use Strings
    //This is because display uses strings rather than char
    private void multiplePrint(String chars, int times)
    {
        String line = "";
        for (int i = 0; i < times; i++)
        {
            line += chars;
        }
        display.append(line);
>>>>>>> gui-development
    }
}
