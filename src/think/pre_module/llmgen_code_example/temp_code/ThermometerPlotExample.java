import org.jfree.chart.plot.ThermometerPlot;
import java.awt.Paint;

public class ThermometerPlotExample {
    public static void main(String[] args) {
        ThermometerPlot plot = new ThermometerPlot();
        Paint paint = Color.RED;
        int range = 1;
        
        plot.setSubrangePaint(range, paint);
    }
}