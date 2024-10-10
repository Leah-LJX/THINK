import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.plot.CenterTextMode;

public class RingPlotExample {
    public static void main(String[] args) {
        RingPlot plot = new RingPlot();
        
        CenterTextMode centerTextMode = plot.getCenterTextMode();
        
        System.out.println("Center text mode: " + centerTextMode);
    }
}