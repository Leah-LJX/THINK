import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveDocumentExample {
    public static void main(String[] args) {
        PDDocument document = new PDDocument();
        try {
            // Add content to the document

            // Save the document to an output stream
            OutputStream output = new FileOutputStream("output.pdf");
            document.save(output);
            output.close();
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