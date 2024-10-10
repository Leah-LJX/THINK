import com.example.PublicKeyProtectionPolicy;

public class DocumentEncryption {
    public static void main(String[] args) {
        PublicKeyProtectionPolicy recip = new PublicKeyProtectionPolicy();
        recip.setX509(certificate);
        
        // Add recipient to the policy
        policy.addRecipient(recip);
        
        // Set encryption key length
        policy.setEncryptionKeyLength(128);
        
        // Protect the document
        doc.protect(policy);
        
        // Save the document
        doc.save(out);
    }
}