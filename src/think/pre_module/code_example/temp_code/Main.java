import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.CompressParameters;

public class Main {
    public static void main(String[] args) {
        PDDocument document = new PDDocument();
        CompressParameters compressParameters = new CompressParameters();

        // ... code to create or modify the document

        document.save("example.pdf", compressParameters);
    }
}