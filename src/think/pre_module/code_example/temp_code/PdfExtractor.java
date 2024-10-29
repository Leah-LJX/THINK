import com.example.AccessPermission;

public class PdfExtractor {
    public static void main(String[] args) {
        AccessPermission accessPermission = new AccessPermission();

        boolean canExtract = accessPermission.canExtractForAccessibility();
        System.out.println("Can extract for accessibility: " + canExtract);
    }
}