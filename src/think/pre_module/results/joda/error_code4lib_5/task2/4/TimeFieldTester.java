import org.joda.time.DurationField;

public class TimeFieldTester {
    public void testMethodsThatShouldAlwaysReturnNull() {
        DurationField rangeDurationField = getRangeDurationField();
        DurationField leapDurationField = getLeapDurationField();

        if (rangeDurationField == null && leapDurationField == null) {
            System.out.println("Both getRangeDurationField() and getLeapDurationField() return null as expected.");
        } else {
            System.out.println("At least one of getRangeDurationField() and getLeapDurationField() does not return null.");
        }
    }
}