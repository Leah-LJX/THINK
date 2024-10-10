import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageXYZDestination;

public class ZoomExample {
    public static void main(String[] args) {
        PDPageXYZDestination destination = new PDPageXYZDestination();
        float zoom = 1.5f;
        
        destination.setZoom(zoom);
    }
}