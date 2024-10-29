import com.adobe.acrobat.security.StandardProtectionPolicy;
import com.adobe.acrobat.security.AccessPermission;

public class PdfProtectionExample {
    public static void main(String[] args) {
        String ownerPassword = "owner123";
        String userPassword = "user123";
        AccessPermission permissions = new AccessPermission();
        
        StandardProtectionPolicy protectionPolicy = new StandardProtectionPolicy(ownerPassword, userPassword, permissions);
    }
}