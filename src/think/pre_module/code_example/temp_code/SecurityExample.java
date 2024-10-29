import com.example.security.StandardSecurityHandler;

public class SecurityExample {
    public static void main(String[] args) {
        String password = "examplePassword";
        byte[] user = { 0x00, 0x01, 0x02 };
        byte[] owner = { 0x03, 0x04, 0x05 };
        int permissions = 7;
        byte[] id = { 0x06, 0x07, 0x08 };
        int encRevision = 2;
        int keyLengthInBytes = 128;
        boolean encryptMetadata = true;

        StandardSecurityHandler securityHandler = new StandardSecurityHandler();
        boolean isOwner = securityHandler.isOwnerPassword(password, user, owner, permissions, id, encRevision, keyLengthInBytes, encryptMetadata);
        
        System.out.println("Is owner password: " + isOwner);
    }
}