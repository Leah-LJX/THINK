import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;

public class YIntervalSeriesExample {
    public static void main(String[] args) {
        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        YIntervalSeries series1 = new YIntervalSeries("Series 1");
        series1.add(1.0, 0.5, 1.5, 0.2, 0.8);
        dataset.addSeries(series1);

        // Removing the series
        dataset.removeSeries(series1);
    }
}