import java.time.LocalDateTime;
import java.util.Date;

public class DateConverter {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        System.out.println("Converted date: " + date);
    }
}