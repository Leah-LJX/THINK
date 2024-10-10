import org.joda.time.Weeks;

public class WeeksConverter {
    public static void main(String[] args) {
        Weeks weeks = Weeks.weeks(2);
        int standardSeconds = weeks.toStandardSeconds().getSeconds();
        System.out.println("Standard seconds: " + standardSeconds);
    }
}