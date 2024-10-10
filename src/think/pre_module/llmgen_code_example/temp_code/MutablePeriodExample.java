import org.joda.time.MutablePeriod;
import org.joda.time.Chronology;

public class MutablePeriodExample {
    public static void main(String[] args) {
        long startInstant = System.currentTimeMillis();
        long endInstant = System.currentTimeMillis() + 1000;
        Chronology chrono = Chronology.getDefault();

        MutablePeriod period = new MutablePeriod();
        period.setPeriod(startInstant, endInstant, chrono);
    }
}