import org.joda.time.Instant;
import org.joda.time.MutableDateTime;
import org.joda.time.chrono.ISOChronology;

public class MutableDateTimeExample {
    public static void main(String[] args) {
        Instant instant = new Instant(); // Instantiate an Instant object (can use actual timestamp as parameter)
        MutableDateTime mutableDateTime = instant.toMutableDateTime();
        
        System.out.println("MutableDateTime: " + mutableDateTime);
    }
}