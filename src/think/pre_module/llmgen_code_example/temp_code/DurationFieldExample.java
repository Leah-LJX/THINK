import org.joda.time.field.BaseDurationField;

public class DurationFieldExample {
    public static void main(String[] args) {
        BaseDurationField durationField = new BaseDurationField();

        String debugString = durationField.toString();

        System.out.println("Debug String: " + debugString);
    }
}