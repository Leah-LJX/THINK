import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFMergerUtility;

public class PdfMerger {
    public static void main(String[] args) {
        PDDocument destination = new PDDocument();
        PDDocument source = new PDDocument();

        // perform some operations on the destination and source documents

        PDFMergerUtility pdfMerger = new PDFMergerUtility();
        pdfMerger.appendDocument(destination, source);

        // other code
    }
}