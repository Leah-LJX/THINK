import org.joda.time.ReadableInterval;

public class OverlapChecker {
    public boolean checkIfOverlaps(ReadableInterval interval1, ReadableInterval interval2) {
        return interval1.overlaps(interval2);
    }
}