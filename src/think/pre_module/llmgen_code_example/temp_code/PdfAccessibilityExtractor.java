import com.example.AccessPermission;

public class PdfAccessibilityExtractor {
    public static void main(String[] args) {
        AccessPermission permission = new AccessPermission();
        boolean canExtract = permission.canExtractForAccessibility();
        System.out.println("User can extract for accessibility: " + canExtract);
    }
}