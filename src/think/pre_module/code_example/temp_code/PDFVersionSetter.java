import org.apache.pdfbox.pdmodel.PDDocumentCatalog;

public class PDFVersionSetter {
    public static void main(String[] args) {
        PDDocumentCatalog documentCatalog = new PDDocumentCatalog();
        String version = "1.7";
        
        documentCatalog.setVersion(version);
    }
}