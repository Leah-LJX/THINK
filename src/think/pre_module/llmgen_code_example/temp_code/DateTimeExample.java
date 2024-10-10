import org.joda.time.DateTime;

public class DateTimeExample {
    public static void main(String[] args) {
        AbstractInstant instant = new AbstractInstant(); // initialize with actual data

        DateTime dateTime = instant.toDateTimeISO();
        System.out.println("DateTime using ISOChronology in the same zone: " + dateTime);
    }
}