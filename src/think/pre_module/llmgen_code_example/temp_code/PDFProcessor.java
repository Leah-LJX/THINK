import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFProcessor {
    public void processPDF() {
        PDPageTree pages = getPdfPages(); // method to obtain PDF pages
        PDFTextStripper textStripper = new PDFTextStripper();
        textStripper.processPages(pages);
    }
}