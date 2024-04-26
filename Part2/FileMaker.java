
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class FileMaker
{
    public static void main(String [] args)
    {
        try (PrintWriter p = new PrintWriter(new FileWriter("users.txt")))
        {
            p.println("Muneeb,Musharaf,69");
            p.println("Moeez,Musharaf,170");
        }
        catch(IOException e){}
        
        try (PrintWriter q = new PrintWriter(new FileWriter("horseslist.txt")))
        {
            q.println("Ava,B,0.8,4.5,2,1");
            q.println("Bolton,W,0.4,7.5,5,0");
            q.println("Coward,B,0.8,3.2,0,0");
        }
        catch(IOException e){}
    }
}
