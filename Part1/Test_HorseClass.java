public class Test
{
    public static void main(String [] args)
    {
        System.out.println("HORSE CLASS TEST");

        Horse test_horse = new Horse('t',"test_horse1", 0.5);

        System.out.println("hasFallen() test: Expected: false");
        System.out.println("Result: " + test_horse.hasFallen());
        System.out.println("");

        System.out.println("getConfidence() test1: Expected: 0.5");
        System.out.println("Result: " + test_horse.getConfidence());
        System.out.println("");

        System.out.println("getDistanceTravelled() test1: Expected: 0");
        System.out.println("Result: " + test_horse.getDistanceTravelled());
        System.out.println("");

        System.out.println("getName() test: Expected: test_horse1");
        System.out.println("Result: " + test_horse.getName());
        System.out.println("");

        System.out.println("getSymbol() test: Expected: t");
        System.out.println("Result: " + test_horse.getSymbol());
        System.out.println("");

        test_horse.moveForward(); //Should increase distance by 1
        test_horse.moveForward(); //Should increase distance by 1 again making it 2 now
        System.out.println("moveForward test: Expected: 2");
        System.out.println("Result: " + test_horse.getDistanceTravelled());
        System.out.println("");

        test_horse.setConfidence(0.67); //Should change confidence to 0.67
        System.out.println("setConfidence() test: Expected: 0.67");
        System.out.println("Result: " + test_horse.getConfidence());
        System.out.println("");

        test_horse.setSymbol('s'); //Should change symbol to s
        System.out.println("setSymbol() test: Expected: s");
        System.out.println("Result: " + test_horse.getSymbol());
        System.out.println("");

        test_horse.goBackToStart(); //Should revert to 0
        System.out.println("goBackToStart() test: Expected: 0");
        System.out.println("Result: " + test_horse.getDistanceTravelled());
        System.out.println("");

        test_horse.fall(); //Should change the result to true
        System.out.println("fall() test: Expected: true");
        System.out.println("Result: " + test_horse.hasFallen());


    }
}
