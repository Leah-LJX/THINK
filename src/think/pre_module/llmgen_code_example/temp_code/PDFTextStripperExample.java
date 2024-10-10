import org.apache.pdfbox.text.PDFTextStripper;

public class PDFTextStripperExample {

    public static void main(String[] args) {
        PDFTextStripper pdfTextStripper = new PDFTextStripper();

        // Write something at the start of a page
        pdfTextStripper.writePageStart("This is the start of the page.");
    }
}