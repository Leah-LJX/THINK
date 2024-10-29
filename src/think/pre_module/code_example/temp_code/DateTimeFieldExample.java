import org.joda.time.base.BaseDateTimeField;

public class DateTimeFieldExample {
    public static void main(String[] args) {
        long minuendInstant = 1643121600000L; // Example value
        long subtrahendInstant = 1643059200000L; // Example value

        BaseDateTimeField field = new BaseDateTimeField(); // Example instantiation

        int difference = field.getDifference(minuendInstant, subtrahendInstant);
        System.out.println("Difference: " + difference);
    }
}