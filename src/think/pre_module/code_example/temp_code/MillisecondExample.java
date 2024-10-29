import java.util.Date;
import java.util.TimeZone;
import java.util.Locale;

public class MillisecondExample {
    public static void main(String[] args) {
        Date time = new Date();
        TimeZone zone = TimeZone.getDefault();
        Locale locale = Locale.getDefault();
        
        Millisecond milli = new Millisecond(time, zone, locale);
    }
}