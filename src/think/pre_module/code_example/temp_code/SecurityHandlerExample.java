import com.example.security.StandardSecurityHandler;

public class SecurityHandlerExample {

    public static void main(String[] args) {
        String password = "examplePassword";
        byte[] user = new byte[]{1, 2, 3};
        byte[] owner = new byte[]{4, 5, 6};
        int permissions = 7;
        byte[] id = new byte[]{7, 8, 9};
        int encRevision = 2;
        int keyLengthInBytes = 128;
        boolean encryptMetadata = true;

        StandardSecurityHandler securityHandler = new StandardSecurityHandler();
        boolean isOwner = securityHandler.isOwnerPassword(password, user, owner, permissions, id, encRevision, keyLengthInBytes, encryptMetadata);
        System.out.println("Is Owner Password: " + isOwner);
    }
}