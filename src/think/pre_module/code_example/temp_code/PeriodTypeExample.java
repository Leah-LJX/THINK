import org.joda.time.base.BasePeriod;
import org.joda.time.PeriodType;

public class PeriodTypeExample {
    public static void main(String[] args) {
        BasePeriod basePeriod = new BasePeriod();
        PeriodType periodType = basePeriod.getPeriodType();
        System.out.println("Period Type: " + periodType);
    }
}