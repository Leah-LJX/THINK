import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.Loader;

public class PDFLoader {
    public static void main(String[] args) {
        byte[] pdfBytes = // initialize with actual PDF bytes
        String password = "myPassword"; // initialize with actual password or set to null if no password
        try {
            PDDocument document = Loader.loadPDF(pdfBytes, password);
            // process the loaded PDF document
            document.close();  // don't forget to close the document after processing
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}