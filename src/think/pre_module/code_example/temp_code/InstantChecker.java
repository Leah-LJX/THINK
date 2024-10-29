import org.joda.time.ReadableInstant;

public class InstantChecker {
    public ReadableInstant initialInstant;
    
    public InstantChecker(ReadableInstant initialInstant) {
        this.initialInstant = initialInstant;
    }
    
    public boolean isAfter(ReadableInstant instant) {
        return initialInstant.isAfter(instant);
    }
}