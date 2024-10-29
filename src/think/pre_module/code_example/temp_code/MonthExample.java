import org.jfree.date.SerialDate;

public class MonthExample {
    public static void main(String[] args) {
        SerialDate serialDate = new SerialDate();
        int month = serialDate.getMonth();
        System.out.println("Month: " + month);
    }
}