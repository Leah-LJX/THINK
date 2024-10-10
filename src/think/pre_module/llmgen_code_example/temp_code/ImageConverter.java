import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;

import java.awt.image.BufferedImage;

public class ImageConverter {
    public static void main(String[] args) {
        PDDocument document = new PDDocument();
        BufferedImage image = // initialize with actual image data
        PDImageXObject jpegImage = JPEGFactory.createFromImage(document, image);
        
        // Rest of the code to work with the PDImageXObject
    }
}