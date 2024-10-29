import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFTextProcessor {
    public void processPages(PDPageTree pages) {
        PDFTextStripper textStripper = new PDFTextStripper();
        textStripper.setSortByPosition(true);
        try {
            for (int i = 0; i < pages.getCount(); i++) {
                textStripper.processPage(pages.get(i));
                String pageText = textStripper.getText(pages.get(i));
                System.out.println("Text on page " + (i+1) + ": " + pageText);
                // additional processing logic here
            }
        } catch (Exception e) {
            System.out.println("Error processing PDF: " + e.getMessage());
        }
    }
}