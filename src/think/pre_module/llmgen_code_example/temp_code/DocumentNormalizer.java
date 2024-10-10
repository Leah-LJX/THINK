import org.w3c.dom.Document;

public class DocumentNormalizer {
    public Document document;

    public DocumentNormalizer(Document document) {
        this.document = document;
    }

    public void normalize() {
        document.normalizeDocument();
    }

    public static void main(String[] args) {
        Document document = null; // initialize with actual document
        DocumentNormalizer normalizer = new DocumentNormalizer(document);
        normalizer.normalize();
    }
}