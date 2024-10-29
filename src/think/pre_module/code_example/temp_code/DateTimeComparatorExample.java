import java.time.LocalDateTime;
import org.joda.time.DateTimeComparator;

public class DateTimeComparatorExample {
    public static void main(String[] args) {
        LocalDateTime date1 = LocalDateTime.of(2022, 4, 15, 10, 30);
        LocalDateTime date2 = LocalDateTime.of(2022, 4, 15, 12, 30);
        
        DateTimeComparator comparator = DateTimeComparator.getDateOnlyInstance();
        int result = comparator.compare(date1, date2);
        
        System.out.println("Comparison result: " + result);
    }
}