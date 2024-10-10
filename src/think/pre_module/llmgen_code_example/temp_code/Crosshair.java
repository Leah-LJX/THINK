import org.jfree.chart.ui.RectangleAnchor;

public class Crosshair {
    public RectangleAnchor labelAnchor;

    public void setLabelAnchor(RectangleAnchor anchor) {
        this.labelAnchor = anchor;
        // Send property change event to listeners
    }
}

// API Usage
RectangleAnchor anchor = RectangleAnchor.TOP_LEFT;
Crosshair crosshair = new Crosshair();
crosshair.setLabelAnchor(anchor);