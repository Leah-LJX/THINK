import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;

public class DocumentTypeExample {
    public static void main(String[] args) {
        Document document = // initialize document object here
        DocumentType documentType = document.getDoctype();
        // make use of documentType object here
    }
}