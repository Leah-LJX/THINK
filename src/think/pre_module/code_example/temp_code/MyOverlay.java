import java.util.List;
import java.util.ArrayList;

public class MyOverlay extends AbstractOverlay {
    public List<OverlayChangeListener> listeners = new ArrayList<>();

    public void addListener(OverlayChangeListener listener) {
        listeners.add(listener);
    }

    public void removeListener(OverlayChangeListener listener) {
        listeners.remove(listener);
    }

    public void makeChanges() {
        // code to make changes to the overlay
        // ...

        // Notify listeners of the changes
        notifyListeners(new OverlayChangeEvent(this));
    }
}