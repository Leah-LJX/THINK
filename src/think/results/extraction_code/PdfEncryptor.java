import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

import java.io.File;
import java.io.IOException;

public class PdfEncryptor {

    public static void encryptPdf(String inputPdfPath, String outputPdfPath, String userPassword, String ownerPassword) {
        PDDocument document = null;
        try {
            // Load the PDF document
            document = PDDocument.load(new File(inputPdfPath));

            // Create access permissions
            AccessPermission accessPermission = new AccessPermission();
            // Restrict printing and copying
            accessPermission.setCanPrint(false);
            accessPermission.setCanModify(false);
            accessPermission.setCanExtractContent(false);

            // Set the standard protection policy
            StandardProtectionPolicy policy = new StandardProtectionPolicy(ownerPassword, userPassword, accessPermission);
            policy.setEncryptionKeyLength(128); // Use 128-bit AES encryption

            // Apply the protection policy to the document
            document.protect(policy);

            // Save the encrypted document
            document.save(outputPdfPath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        // Example usage
        String inputPdfPath = "path/to/input.pdf";
        String outputPdfPath = "path/to/output.pdf";
        String userPassword = "user_password";
        String ownerPassword = "owner_password";

        encryptPdf(inputPdfPath, outputPdfPath, userPassword, ownerPassword);
    }
}