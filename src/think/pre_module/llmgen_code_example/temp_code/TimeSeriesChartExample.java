import org.jfree.data.xy.XYDataset;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;

public class TimeSeriesChartExample {
    public static void main(String[] args) {
        String title = "Stock Prices";
        String timeAxisLabel = "Date";
        String valueAxisLabel = "Price";
        XYDataset dataset = createDataset(); // Assuming createDataset() method exists
        boolean legend = true;
        boolean tooltips = true;
        boolean urls = false;
        
        JFreeChart chart = ChartFactory.createTimeSeriesChart(title, timeAxisLabel, valueAxisLabel, dataset, legend, tooltips, urls);
        
        // Further code to display or save the chart
    }
}