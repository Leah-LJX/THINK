import java.util.ArrayList;
import java.util.List;

public class OverlayExample {
    public List<OverlayChangeListener> listeners = new ArrayList<>();
    public AbstractOverlay overlay;

    public void addListener(OverlayChangeListener listener) {
        listeners.add(listener);
    }

    public void removeListener(OverlayChangeListener listener) {
        listeners.remove(listener);
    }

    protected void notifyListeners(OverlayChangeEvent event) {
        for (OverlayChangeListener listener : listeners) {
            listener.overlayChanged(event);
        }
    }
}

interface OverlayChangeListener {
    void overlayChanged(OverlayChangeEvent event);
}

class OverlayChangeEvent {
    //define event properties
}

class AbstractOverlay {
    //define overlay properties and behavior
}