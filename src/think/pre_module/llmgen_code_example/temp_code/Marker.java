import java.util.ArrayList;
import java.util.List;

public class Marker {
    public List<MarkerChangeListener> listeners = new ArrayList<>();

    public void notifyListeners(MarkerChangeEvent event) {
        for (MarkerChangeListener listener : listeners) {
            listener.onMarkerChange(event);
        }
    }

    // Other methods and variables can be defined here
}

interface MarkerChangeListener {
    void onMarkerChange(MarkerChangeEvent event);
}

class MarkerChangeEvent {
    // Event details can be defined here
}