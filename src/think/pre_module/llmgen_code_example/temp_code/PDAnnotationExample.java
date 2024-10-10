import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationPolygon;

public class PDAnnotationExample {
    public static void main(String[] args) {
        PDPage page = new PDPage(PDRectangle.A4);
        PDAnnotationPolygon annotation = new PDAnnotationPolygon();

        // Set up annotation properties
        // ...

        PDColor interiorColor = annotation.getInteriorColor();
        System.out.println("Interior Color: " + interiorColor.toString());
    }
}