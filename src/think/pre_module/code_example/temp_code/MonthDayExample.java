import org.joda.time.DateTimeZone;
import org.joda.time.MonthDay;

public class MonthDayExample {
    public static void main(String[] args) {
        DateTimeZone zone = DateTimeZone.UTC;
        MonthDay currentMonthDay = MonthDay.now(zone);
        System.out.println("Current MonthDay: " + currentMonthDay);
    }
}