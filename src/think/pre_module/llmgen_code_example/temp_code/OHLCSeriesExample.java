import org.jfree.data.xy.OHLCSeries;
import org.jfree.data.xy.OHLCSeriesCollection;

public class OHLCSeriesExample {

    public static void main(String[] args) {
        OHLCSeriesCollection collection = new OHLCSeriesCollection();

        OHLCSeries series1 = new OHLCSeries("Series1");
        // Add data to series1

        collection.addSeries(series1);

        boolean removed = collection.removeSeries(series1);
    }
}