import org.joda.time.Period;
import org.joda.time.Weeks;

public class PeriodConverter {
    public static void main(String[] args) {
        Period period = new Period(2, 0, 0, 0); // 2 years
        Weeks weeks = period.toStandardWeeks();
        int weeksValue = weeks.getWeeks();
        System.out.println("Weeks: " + weeksValue);
    }
}