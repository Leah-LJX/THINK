import org.apache.pdfbox.pdmodel.PDDocumentInformation;

public class CustomMetadataExample {
    public static void main(String[] args) {
        PDDocumentInformation documentInformation = new PDDocumentInformation();
        String fieldName = "customField";
        String fieldValue = "customValue";

        documentInformation.setCustomMetadataValue(fieldName, fieldValue);
    }
}