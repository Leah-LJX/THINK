import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.IOException;

public class PdfDocumentHandler {
    public static void main(String[] args) {
        PDDocument document = new PDDocument();
        try {
            // Add content to the document

            // Save the document to a file using default compression
            document.save("output.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}