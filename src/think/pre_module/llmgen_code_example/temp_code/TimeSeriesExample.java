import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;

public class TimeSeriesExample {

    public static void main(String[] args) {
        TimeSeries originalTimeSeries = new TimeSeries("Example TimeSeries");
        
        // Add data to originalTimeSeries
        
        RegularTimePeriod start = originalTimeSeries.getTimePeriod(0);
        RegularTimePeriod end = originalTimeSeries.getTimePeriod(originalTimeSeries.getItemCount() - 1);
        
        TimeSeries copiedTimeSeries = originalTimeSeries.createCopy(start, end);
    }
}