import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.Layer;

public class MarkerExample {
    public static void main(String[] args) {
        CategoryPlot plot = new CategoryPlot();
        Marker marker = new Marker();
        Layer layer = Layer.FOREGROUND;

        plot.addRangeMarker(1, marker, layer);
    }
}