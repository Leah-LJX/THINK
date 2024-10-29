import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.ReadableInterval;

public class TimeIntervalChecker {
    public static void main(String[] args) {
        DateTime start1 = new DateTime(2022, 1, 1, 0, 0);
        DateTime end1 = new DateTime(2022, 1, 15, 0, 0);
        Interval interval1 = new Interval(start1, end1);
        
        DateTime start2 = new DateTime(2022, 1, 5, 0, 0);
        DateTime end2 = new DateTime(2022, 1, 10, 0, 0);
        Interval interval2 = new Interval(start2, end2);
        
        boolean result = interval1.contains(interval2);
        System.out.println("Interval 2 is contained within interval 1: " + result);
    }
}