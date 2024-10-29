import org.joda.time.ReadableInstant;
import org.joda.time.Weeks;

public class WeekCalculator {
    public static void main(String[] args) {
        ReadableInstant start = // initialize with a start datetime
        ReadableInstant end = // initialize with an end datetime

        Weeks weeksBetween = Weeks.weeksBetween(start, end);
        System.out.println("Number of whole weeks between start and end: " + weeksBetween.getWeeks());
    }
}