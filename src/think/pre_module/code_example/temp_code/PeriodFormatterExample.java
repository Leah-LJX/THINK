import org.joda.time.PeriodType;

public class PeriodFormatterExample {
    public static void main(String[] args) {
        PeriodFormatter formatter = new PeriodFormatter();
        PeriodType parseType = formatter.getParseType();
        System.out.println("Parse Type: " + parseType);
    }
}