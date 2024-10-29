import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

public class LocalDateTimeExample {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        TimeZone timeZone = TimeZone.getTimeZone("GMT");

        Date date = localDateTime.toDate(timeZone);
        System.out.println(date);
    }
}