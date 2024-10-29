import java.time.LocalDateTime;
import java.util.TimeZone;
import java.util.Date;

public class DateTimeConverter {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        TimeZone timeZone = TimeZone.getTimeZone("UTC");

        Date date = localDateTime.toDate(timeZone);
        System.out.println("Date converted from local date time: " + date);
    }
}