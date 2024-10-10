import org.jfree.data.time.SimpleTimePeriod;

public class TimePeriodExample {
    public static void main(String[] args) {
        SimpleTimePeriod timePeriod = new SimpleTimePeriod(1620061200000L, 1620064800000L);
        long startMillis = timePeriod.getStartMillis();
        System.out.println("Start time in milliseconds: " + startMillis);
    }
}