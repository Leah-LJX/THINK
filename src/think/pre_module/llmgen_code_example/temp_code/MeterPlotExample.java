import org.jfree.chart.plot.MeterPlot;

public class MeterPlotExample {

    public static void main(String[] args) {
        // Create a MeterPlot instance
        MeterPlot originalPlot = new MeterPlot();

        // Set some properties of the original plot
        originalPlot.setUnits("Widgets");
        originalPlot.setRange(0, 100);

        // Clone the original plot
        MeterPlot clonedPlot = (MeterPlot) originalPlot.clone();

        // Use the cloned plot
        // ...
    }
}