import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class TimeSeriesCollectionExample {
    public static void main(String[] args) {
        TimeSeries series1 = new TimeSeries("Series 1");
        TimeSeries series2 = new TimeSeries("Series 2");

        // Add data to the series...

        TimeSeriesCollection collection = new TimeSeriesCollection();
        collection.addSeries(series1);
        collection.addSeries(series2);

        // ...

        collection.removeSeries(series2);
    }
}