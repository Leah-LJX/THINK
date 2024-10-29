import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormatter;

public class PeriodFormatterUsage {
    public static void main(String[] args) {
        PeriodFormatter formatter = new PeriodFormatter();
        PeriodType parseType = formatter.getParseType();
        System.out.println("Parse type: " + parseType);
    }
}