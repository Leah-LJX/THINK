import org.joda.time.DateTimeZone;
import java.util.Set;

public class DateTimeZoneExample {
    public static void main(String[] args) {
        Set<String> availableIDs = DateTimeZone.getAvailableIDs();
        for (String id : availableIDs) {
            System.out.println("Available ID: " + id);
            DateTimeZone zone = DateTimeZone.forID(id);
            System.out.println("Offset: " + zone.getOffset(1000L));
        }
    }
}