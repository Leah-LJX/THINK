import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.File;

public class PdfParser {
    public static void main(String[] args) {
        File pdfFile = new File("myPdf.pdf");
        
        PDDocument doc = Loader.loadPDF(pdfFile);
        
        if (doc != null) {
            AccessPermission accessPerm = doc.getCurrentAccessPermission();
            boolean canExtract = accessPerm.canExtractForAccessibility();
            System.out.println("Can extract for accessibility: " + canExtract);
        } else {
            System.out.println("Failed to parse the PDF.");
        }
    }
}