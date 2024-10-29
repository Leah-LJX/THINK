import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFMergerUtility;
import java.io.File;
import org.apache.pdfbox.io.RandomAccessRead;

public class PDFManipulator {
    public static void main(String[] args) {
        try {
            // Load a PDF document
            File file1 = new File("document1.pdf");
            PDDocument doc1 = Loader.loadPDF(file1);

            File file2 = new File("document2.pdf");
            PDDocument doc2 = Loader.loadPDF(file2);

            // Merge the documents
            PDFMergerUtility merger = new PDFMergerUtility();
            merger.addSource(doc1);
            merger.addSource(doc2);
            merger.setDestinationFileName("mergedDocument.pdf");
            merger.mergeDocuments();

            // Close the documents
            doc1.close();
            doc2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}