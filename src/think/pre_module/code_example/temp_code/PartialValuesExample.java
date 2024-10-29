import org.joda.time.chrono.ISOChronology;
import org.joda.time.DateTimeFieldType;
import org.joda.time.ReadablePartial;
import org.joda.time.format.DateTimeFormatter;

public class PartialValuesExample {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.forPattern("yyyy-MM-dd");
        Object object = "2022-05-15";
        ISOChronology chrono = ISOChronology.getInstance();
        
        AbstractConverter converter = new AbstractConverter();
        int[] partialValues = converter.getPartialValues(DateTimeFieldType.year(), object, chrono, formatter);
        
        for (int value : partialValues) {
            System.out.println(value);
        }
    }
}