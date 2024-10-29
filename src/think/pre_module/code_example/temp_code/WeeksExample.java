import org.joda.time.ReadableInstant;
import org.joda.time.Weeks;

public class WeeksExample {
   public static void main(String[] args) {
      ReadableInstant start = ...; //initialize with start date
      ReadableInstant end = ...; //initialize with end date
      
      Weeks weeks = Weeks.weeksBetween(start, end);
      System.out.println("Number of whole weeks between the two dates: " + weeks.getWeeks());
   }
}