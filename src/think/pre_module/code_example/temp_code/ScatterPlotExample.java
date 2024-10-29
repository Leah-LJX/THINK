import org.jfree.chart.plot.FastScatterPlot;
import org.jfree.chart.axis.ValueAxis;

public class ScatterPlotExample {
    public static void main(String[] args) {
        FastScatterPlot scatterPlot = new FastScatterPlot();
        ValueAxis rangeAxis = new ValueAxis();

        scatterPlot.setRangeAxis(rangeAxis);
    }
}