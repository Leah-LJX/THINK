import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class DateTimeFormatterExample {
    public static void main(String[] args) {
        DateTimeFormatter formatter = ISODateTimeFormat.date();
        String formattedDate = formatter.print(new DateTime());
        System.out.println("Formatted date: " + formattedDate);
    }
}