import java.util.concurrent.TimeUnit;
import java.lang.Math;
import java.time.Instant;
import java.time.Duration;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import java.awt.Color;

public class Race
{
    private int raceLength;
    private Horse lane1Horse;
    private Horse lane2Horse;
    private Horse lane3Horse;
    private JTextArea display;

    //declare a local variable to tell us when the race is finished
    boolean finished = false;
    
    

    public Race(int distance, JTextArea display)
    {
        // initialise instance variables
        raceLength = distance;
        lane1Horse = null;
        lane2Horse = null;
        lane3Horse = null;
        this.display = display;
    }

    
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
                    if (raceWonBy(lane1Horse))
                    {
                        if (raceWonBy(lane2Horse))
                        {
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
                        }
                    }
                    else if (raceWonBy(lane2Horse))
                    {
                        if (raceWonBy(lane1Horse))
                        {
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
                        }
                    }
                    else
                    {
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

        UpdateFile(lane1Horse);
        UpdateFile(lane2Horse);
        UpdateFile(lane3Horse);
    }

    private void moveHorse(Horse theHorse)
    {
        //if the horse has fallen it cannot move, 
        //so only run if it has not fallen
        if  (!theHorse.hasFallen())
        {
            //the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence())
            {
                theHorse.moveForward();
            }

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
    }

    private boolean raceWonBy(Horse theHorse)
    {
        if ((theHorse.getDistanceTravelled() == raceLength && theHorse.hasFallen() == true) || (theHorse.getDistanceTravelled() == raceLength))
        {
            theHorse.GainWin();
            return true;
        }
        else
        {
            return false;
        }
    }

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
        else if (raceWonBy(theHorse))
        {
            double new_confidence1 = Math.floor((theHorse.getConfidence()+0.1)*100)/100;
            theHorse.setConfidence(new_confidence1);
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

    public void UpdateFile(Horse theHorse)
    {
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
                if (Double.parseDouble(parts[2]) == 0)
                {
                    sb.append(line).append("\n");
                }
                else if (parts[0].equals(targetName) && parts[1].equals(targetSymbol))
                {
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

    private void multiplePrint(String chars, int times)
    {
        String line = "";
        for (int i = 0; i < times; i++)
        {
            line += chars;
        }
        display.append(line);
    }
}