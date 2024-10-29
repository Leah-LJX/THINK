import org.joda.time.DateTimeZone;
import java.util.TimeZone;

public class TimeZoneExample {
    public static void main(String[] args) {
        DateTimeZone dateTimeZone = DateTimeZone.forID("Europe/London");
        TimeZone timeZone = dateTimeZone.toTimeZone();
    }
}