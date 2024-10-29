import org.jfree.data.time.SerialDate;

public class MonthNamesExample {
    public static void main(String[] args) {
        boolean shortened = true;
        String[] months = SerialDate.getMonths(shortened);
        for (String month : months) {
            System.out.println(month);
        }
    }
}