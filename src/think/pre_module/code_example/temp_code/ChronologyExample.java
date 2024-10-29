import org.joda.time.chrono.BaseChronology;

public class ChronologyExample {
    public static void main(String[] args) {
        BaseChronology chronology = new BaseChronology();
        long instant = 1000L;
        long duration = 500L;
        int scalar = 2;
        
        long result = chronology.add(instant, duration, scalar);
        System.out.println("Result: " + result);
    }
}