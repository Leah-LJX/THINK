import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

import java.io.File;
import java.io.IOException;

public class PDFEncryptor {

    public static void encryptPdf(String inputPdfPath, String outputPdfPath, String userPassword, String ownerPassword) {
        try {
            File inputFile = new File(inputPdfPath);
            PDDocument document = PDDocument.load(inputFile);

            AccessPermission accessPermission = new AccessPermission();
            accessPermission.setCanPrint(false);
            accessPermission.setCanExtractContent(false);

            StandardProtectionPolicy protectionPolicy = new StandardProtectionPolicy(ownerPassword, userPassword, accessPermission);
            protectionPolicy.setEncryptionKeyLength(128);
            protectionPolicy.setPermissions(accessPermission);

            PDDocumentCatalog documentCatalog = document.getDocumentCatalog();
            documentCatalog.setEncryptionDictionary(protectionPolicy);

            document.save(outputPdfPath);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}