
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
    double speed;

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
        this.speed = 0;
    }


    //Other methods of class Horse
    public void fall()

    {
        this.fallen = true;
    }
    
    public double getSpeed()
    {
        return this.speed;
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
    
    public void setSpeed(double newSpeed)
    {
        this.speed = newSpeed;
    }
    
    
}