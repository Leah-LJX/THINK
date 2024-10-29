import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageToPDFConverter {

    public static void main(String[] args) {
        try {
            PDDocument document = new PDDocument();
            File imageFile = new File("input.jpg");
            BufferedImage image = ImageIO.read(imageFile);
            float quality = 0.7f;
            int dpi = 300;

            PDImageXObject jpegImage = PDImageXObject.createFromImage(document, image, quality, dpi);

            // Use jpegImage in your PDF document
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}