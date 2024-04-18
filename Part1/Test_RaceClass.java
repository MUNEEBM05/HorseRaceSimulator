public class Test_RaceClass
{
    public static void main(String [] args)
    {
        System.out.println("RACE CLASS TEST");

        Race race_1 = new Race(10);
        Horse race_horse1 = new Horse('\u265E',"race_horse1", 0.8);
        Horse race_horse2 = new Horse('\u2658',"race_horse2", 0.8);
        Horse race_horse3 = new Horse('\u2655',"race_horse3", 0.8);
        race_1.addHorse(race_horse1, 1);
        race_1.addHorse(race_horse2, 2);
        race_1.addHorse(race_horse3, 3);


        race_1.startRace();

    }
}