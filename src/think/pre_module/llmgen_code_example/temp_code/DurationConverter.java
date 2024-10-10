import org.joda.time.ReadableDuration;
import org.joda.time.Period;

public class DurationConverter {
    public static void main(String[] args) {
        // Create a ReadableDuration object
        ReadableDuration duration = new ReadableDuration() {
            @Override
            public long getMillis() {
                return 1000; // Replace with actual duration in milliseconds
            }
        };
        
        // Convert the duration to a Period
        Period period = duration.toPeriod();
        
        // Print the period
        System.out.println("Period: " + period);
    }
}