import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdfparser.COSParser;

public class PDFHandler {
    public void processPDF() {
        COSParser parser = new COSParser();
        COSDictionary root = // initialize with the COSDictionary to be checked

        parser.checkPages(root);
    }
}