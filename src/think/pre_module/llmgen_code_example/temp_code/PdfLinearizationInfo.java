import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSDictionary;

public class PdfLinearizationInfo {
    public static void main(String[] args) {
        // Assume cosDocument is initialized with a valid COSDocument object
        COSDocument cosDocument = new COSDocument();

        // Get the linearization dictionary
        COSDictionary linearizedDictionary = cosDocument.getLinearizedDictionary();
    }
}