import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class PDImageExample {
    public static void main(String[] args) {
        PDDocument document = new PDDocument();
        byte[] imageBytes = new byte[]{ /* image bytes */ };
        String imageName = "exampleImage";
        
        PDImageXObject imageObject = PDImageXObject.createFromByteArray(document, imageBytes, imageName);
        
        // Rest of the code
    }
}