import org.apache.pdfbox.pdmodel.PDDocument;

public class PDFUtil {
    public void saveDocumentToFile(String fileName, CompressParameters compressParameters) {
        PDDocument document = new PDDocument();
        // add content to the document
        // ...
        document.save(fileName, compressParameters);
        document.close();
    }
}