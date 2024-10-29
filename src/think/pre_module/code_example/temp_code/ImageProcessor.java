import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.io.IOException;

public class ImageProcessor {
    public static void main(String[] args) {
        PDDocument document = new PDDocument();
        String imagePath = "example.jpg";
        
        try {
            PDImageXObject imageXObject = PDImageXObject.createFromFile(imagePath, document);
            // Use the imageXObject for further processing
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Close the document
        try {
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}