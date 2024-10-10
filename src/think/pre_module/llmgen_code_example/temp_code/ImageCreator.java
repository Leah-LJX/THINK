import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.image.BufferedImage;

public class ImageCreator {
    public static void main(String[] args) {
        PDDocument document = new PDDocument();
        BufferedImage image = // define the BufferedImage
        float quality = 0.75f;
        int dpi = 300;

        PDImageXObject jpegImage = JPEGFactory.createFromImage(document, image, quality, dpi);
        // further processing or saving the jpegImage
    }
}