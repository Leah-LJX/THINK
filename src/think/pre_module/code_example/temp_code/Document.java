import org.apache.pdfbox.pdmodel.PDDocumentInformation;

public class Document {
    public static void main(String[] args) {
        PDDocumentInformation documentInformation = new PDDocumentInformation();
        String keywords = "apple, banana, orange";
        documentInformation.setKeywords(keywords);
    }
}