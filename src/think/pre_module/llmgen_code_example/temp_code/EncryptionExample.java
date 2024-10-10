import org.apache.pdfbox.cos.COSName;

public class EncryptionExample {
    public static void main(String[] args) {
        COSName streamFilterName = COSName.getPDFName("MyStreamFilter");
        
        PDEncryption encryption = new PDEncryption();
        encryption.setStreamFilterName(streamFilterName);
    }
}