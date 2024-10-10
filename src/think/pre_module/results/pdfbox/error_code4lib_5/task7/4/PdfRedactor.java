import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfRedactor {

    public static void redactKeywordsInPdf(String pdfFilePath, String outputPdfPath, String[] keywords) {
        try {
            PDDocument document = Loader.loadPDF(new File(pdfFilePath), "");
            PDFTextStripper textStripper = new PDFTextStripper();
            for (String keyword : keywords) {
                textStripper.setSearchWord(keyword);
                textStripper.setRedactionColor(0, 0, 0); // Black color for redaction
                textStripper.setRedactAllMatchingRules(true);
                textStripper.stripRedacted(document);
            }
            document.save(outputPdfPath);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}