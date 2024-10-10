import com.example.AccessPermission;

public class DocumentExtractor {
    public static void main(String[] args) {
        AccessPermission permission = new AccessPermission();

        boolean canExtract = permission.canExtractContent();

        System.out.println("User can extract content: " + canExtract);
    }
}