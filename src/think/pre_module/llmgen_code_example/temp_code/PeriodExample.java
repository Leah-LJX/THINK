import java.time.Period;

public class PeriodExample {
    public static void main(String[] args) {
        Period period = Period.of(2, 14, 30); // 2 years, 14 months, 30 days
        period = period.normalizedStandard();
        System.out.println("Normalized period: " + period); 
    }
}