
/**
 * The horse class will be for the features of the horse for the race
 * This includes its symbol, name, confidence which changes in the race, and distance it travels
 * @Muneeb Musharaf
 * @version 1.0
 */
public class Horse
{
    //Fields of class Horse
    String name;
    char symbol;
    int distance;
    boolean fallen;
    double confidence;
    
    //Newly added for Part2
    double speed;
    int loss;
    int win;
    char OGSymbol;
    boolean didwin;

    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
        this.symbol = horseSymbol;
        this.name = horseName;
        this.confidence = horseConfidence;
        this.distance = 0;
        this.fallen = false;
        
        //Newly added for Part2
        this.speed = 0;
        this.win = 0;
        this.loss = 0;
        this.OGSymbol = horseSymbol;
        didwin = false;
    }


    //Other methods of class Horse
    public void fall()

    {
        this.fallen = true;
    }
    
    //Newly added for Part2
    //Gets the speed of the horse
    public double getSpeed()
    {
        return this.speed;
    }
    
    //Newly added for Part2
    //Gets the original symbol of the horse
    //This is because the symbol in a race changes to X if fallen
    public char getOGSymbol()
    {
        return this.OGSymbol;
    }
    
    //Newly added for Part2
    //Gets number of wins of the horse
    public int getWin()
    {
        return this.win;
    }
    
    //Newly added for Part2
    //Gets number of losses of the horse
    public int getLoss()
    {
        return this.loss;
    }
    
    public double getConfidence()

    {
        return this.confidence;
    }

    public int getDistanceTravelled()
    {
        return this.distance;
    }

    public String getName()
    {
        return this.name;
    }

    public char getSymbol()
    {
        return this.symbol;
    }

    public void goBackToStart()
    {
        this.distance = 0;
        this.fallen = false;
    }

    public boolean hasFallen()
    {
        return this.fallen;
    }

    public void moveForward()
    {
        this.distance ++;
    }

    public void setConfidence(double newConfidence)
    {
        this.confidence = newConfidence;
    }

    public void setSymbol(char newSymbol)
    {
        this.symbol = newSymbol;
    }
    
    //Newly added for Part2
    //Changes the speed depending on how the horse behaves in the next race
    public void setSpeed(double newSpeed)
    {
        this.speed = newSpeed;
    }
    
    //Newly added for Part2
    //Increases the number of wins and sets didwin to true as the horse has won
    public void GainWin()
    {
        this.win ++;
        didwin = true;
    }
    
    //Newly added for Part2
    //Increases the number of losses and sets didwin to false as the horse has lost
    public void GainLoss()
    {
        this.loss ++;
        didwin = false;
    }
    
    //Newly added for Part2
    //Sets the number of wins
    public void SetWin(int newwin)
    {
        this.win = newwin;
    }
    
    //Newly added for Part2
    //Sets the number of losses
    public void SetLoss(int newloss)
    {
        this.loss = newloss;
    }
    
    //Newly added for Part2
    //This variable is so that we cansee if the horse has won the lastest game
    //gets the boolean true if hors ehas won and false if lost
    Boolean didWin()
    {
        return this.didwin;
    }
}