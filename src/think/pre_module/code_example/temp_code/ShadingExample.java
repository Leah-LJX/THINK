import org.apache.pdfbox.pdmodel.graphics.shading.PDShading;
import org.apache.pdfbox.pdmodel.graphics.color.PDRectangle;

public class ShadingExample {
    public static void main(String[] args) {
        PDShading shading = new PDShading();
        PDRectangle bbox = shading.getBBox();
        System.out.println("Shading Bounding Box: " + bbox);
    }
}