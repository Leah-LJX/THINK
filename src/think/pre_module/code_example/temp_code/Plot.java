import java.util.ArrayList;
import java.util.List;

public class Plot {
    public List<PlotChangeListener> listeners = new ArrayList<>();

    protected void fireChangeEvent() {
        PlotChangeEvent event = new PlotChangeEvent(this);
        for (PlotChangeListener listener : listeners) {
            listener.plotChanged(event);
        }
    }

    // Other methods and properties of the Plot class
}