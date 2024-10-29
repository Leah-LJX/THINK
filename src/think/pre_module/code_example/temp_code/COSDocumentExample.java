import org.apache.pdfbox.cos.COSDocument;

public class COSDocumentExample {
    public static void main(String[] args) {
        COSDocument cosDocument = new COSDocument();
        COSObjectKey key = new COSObjectKey("exampleKey");
        COSObject object = cosDocument.getObjectFromPool(key);
        // Rest of the code to use the returned COSObject
    }
}