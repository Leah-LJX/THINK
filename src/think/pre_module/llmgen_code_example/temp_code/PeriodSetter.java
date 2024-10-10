import org.joda.time.MutablePeriod;
import org.joda.time.ReadableInstant;

public class PeriodSetter {
    public static void main(String[] args) {
        ReadableInstant start = // initialize with start instant
        ReadableInstant end = // initialize with end instant
        
        MutablePeriod period = new MutablePeriod();
        period.setPeriod(start, end);
        
        // Further usage of the period variable
    }
}