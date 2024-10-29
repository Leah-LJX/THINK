import com.example.PublicKeyProtectionPolicy;
import com.example.RecipientInfo;

public class DocumentProcessor {
    public void processDocument(byte[] document, byte[] recipientCertificate, OutputStream out) {
        PublicKeyProtectionPolicy policy = new PublicKeyProtectionPolicy();
        RecipientInfo recip = new RecipientInfo(recipientCertificate);
        policy.addRecipient(recip);
        policy.setEncryptionKeyLength(128);
        Document doc = new Document(document);
        doc.protect(policy);
        doc.save(out);
    }
}