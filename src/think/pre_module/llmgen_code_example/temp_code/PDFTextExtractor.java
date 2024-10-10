import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PDFTextExtractor {
    public static void main(String[] args) {
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        
        File file = new File("example.pdf");
        
        try {
            List<List<TextPosition>> charactersByArticle = pdfTextStripper.getCharactersByArticle();
            // Do something with the charactersByArticle
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}