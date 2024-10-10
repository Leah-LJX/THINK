import org.joda.time.ReadableInstant;
import org.joda.time.ReadableInterval;

public class IntervalExample {

    public static void main(String[] args) {
        ReadableInstant instant = // initialize with a valid instant
        ReadableInterval interval = // initialize with a valid interval

        boolean result = interval.contains(instant);
        System.out.println("Interval contains instant: " + result);
    }
}