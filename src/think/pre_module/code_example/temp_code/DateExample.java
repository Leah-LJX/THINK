import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class DateExample {

    public static void main(String[] args) {
        DateTimeFormatter formatter = ISODateTimeFormat.date();
        String formattedDate = formatter.print(new org.joda.time.LocalDate());
        System.out.println("Formatted date: " + formattedDate);
    }
}