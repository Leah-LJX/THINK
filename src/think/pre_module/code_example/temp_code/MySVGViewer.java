import org.w3c.dom.svg.SVGDocument;

public class MySVGViewer {

    public SVGDocument svgDoc;

    public MySVGViewer(SVGDocument svgDoc) {
        this.svgDoc = svgDoc;
    }

    public void initializeEventListeners(SVGDocument doc) {
        EventListenerInitializer eventListenerInitializer = new EventListenerInitializer();
        eventListenerInitializer.initializeEventListeners(doc);
    }

    public class EventListenerInitializer {
        public void initializeEventListeners(SVGDocument doc) {
            // logic to register event listeners
        }
    }
}