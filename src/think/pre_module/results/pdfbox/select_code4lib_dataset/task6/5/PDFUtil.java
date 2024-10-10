import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

public class PDFUtil {
    public static void splitPdfAndAddHeaderFooter(String pdfFilePath, String outputDir, String headerText, String footerText) {
        try {
            PDDocument document = PDDocument.load(new File(pdfFilePath));
            PDPageTree pages = document.getPages();

            for (int i = 0; i < pages.getCount(); i++) {
                PDPage page = pages.get(i);
                PDDocument newDocument = new PDDocument();
                newDocument.addPage(page);

                PDPageContentStream contentStream = new PDPageContentStream(newDocument, page, PDPageContentStream.AppendMode.APPEND, true, true);
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.newLineAtOffset(50, page.getMediaBox().getHeight() - 20);
                contentStream.showText(headerText);
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.newLineAtOffset(50, 20);
                contentStream.showText(footerText);
                contentStream.endText();

                contentStream.close();

                newDocument.save(outputDir + "/page_" + (i + 1) + ".pdf");
                newDocument.close();
            }

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}