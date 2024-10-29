import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import java.awt.image.BufferedImage;

public class MainClass {
    public static void main(String[] args) {
        PDDocument document = null; // initialize with PDDocument object
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        int pageIndex = 0; // initialize with specific page index
        float scale = 1.0f; // initialize with specific scale

        try {
            BufferedImage image = pdfRenderer.renderImage(pageIndex, scale);
            // make use of the image
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}