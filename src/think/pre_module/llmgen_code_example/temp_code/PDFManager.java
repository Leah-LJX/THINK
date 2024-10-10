import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class PDFManager {
    public static void main(String[] args) {
        PDDocument doc = new PDDocument();
        // Add pages and content to the document
        
        // Save the document with compression
        String fileName = "example.pdf";
        CompressParameters compressParameters = new CompressParameters();
        doc.save(fileName, compressParameters);
    }
}