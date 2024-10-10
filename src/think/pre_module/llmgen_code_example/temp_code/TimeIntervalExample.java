import org.joda.time.Interval;
import org.joda.time.ReadableInterval;

public class TimeIntervalExample {

    public static void main(String[] args) {
        // Define the intervals to compare
        ReadableInterval firstInterval = new Interval(0, 1000);
        ReadableInterval secondInterval = new Interval(1500, 2000);

        // Check if the firstInterval is entirely before the secondInterval
        boolean isBefore = firstInterval.isBefore(secondInterval);

        // Output the result
        System.out.println("First interval is before the second interval: " + isBefore);
    }
}