import org.jfree.chart.plot.PlotChangeEvent;
import org.jfree.chart.plot.SeriesRenderingOrder;

public class PlotOrderSetter {
    public void setSeriesOrder(SeriesRenderingOrder order) {
        // Call the API method to set the series order
        setSeriesRenderingOrder(order);
        // Send a PlotChangeEvent to all registered listeners
        PlotChangeEvent event = new PlotChangeEvent(this);
        notifyListeners(event);
    }
}