import org.joda.time.Duration;

public class DurationExample {
    public static void main(String[] args) {
        long milliseconds = 86400000; // 1 day in milliseconds
        Duration duration = new Duration(milliseconds);
        long standardDays = duration.getStandardDays();
        System.out.println("Standard Days: " + standardDays);
    }
}