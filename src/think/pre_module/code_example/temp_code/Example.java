import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocument.CompressParameters;

public class Example {
    public static void main(String[] args) {
        File file = new File("example.pdf");
        CompressParameters compressParameters = new CompressParameters();

        PDDocument document = new PDDocument();
        // Add content to the document

        document.save(file, compressParameters);
        document.close();
    }
}