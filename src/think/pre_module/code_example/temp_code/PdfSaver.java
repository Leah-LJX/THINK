import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.CompressParameters;

public class PdfSaver {
    public static void main(String[] args) {
        PDDocument document = new PDDocument();
        // ... Add content to the document

        CompressParameters compressParameters = new CompressParameters();
        // ... Set compression parameters

        String fileName = "output.pdf";
        document.save(fileName, compressParameters);
    }
}