import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.PlotChangeEvent;
import org.jfree.chart.plot.Layer;

public class ExampleCode {
    public static void main(String[] args) {
        XYPlot plot = new XYPlot();
        Marker marker = new Marker();
        Layer layer = new Layer();
        
        int index = 0;
        boolean notify = true;
        
        plot.addDomainMarker(index, marker, layer, notify);
        
        PlotChangeEvent event = new PlotChangeEvent(plot);
        // notifies registered listeners about the plot change event
        plot.notifyListeners(event);
    }
}