import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimeParser {
    public static LocalTime parse(String str, DateTimeFormatter formatter) {
        return LocalTime.parse(str, formatter);
    }
}