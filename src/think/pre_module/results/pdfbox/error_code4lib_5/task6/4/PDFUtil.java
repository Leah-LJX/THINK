import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

public class PDFUtil {
    public static void splitPdfAndAddHeaderFooter(String pdfFilePath, String outputDir, String headerText, String footerText) {
        try {
            PDDocument document = PDDocument.load(new File(pdfFilePath));
            int pageNum = 1;
            for (PDPage page : document.getPages()) {
                PDDocument newDocument = new PDDocument();
                PDPage newPage = new PDPage(page.getMediaBox());
                newDocument.addPage(newPage);

                PDPageContentStream contentStream = new PDPageContentStream(newDocument, newPage);
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.newLineAtOffset(50, 750);
                contentStream.showText(headerText);
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.newLineAtOffset(50, 50);
                contentStream.showText(footerText);
                contentStream.endText();

                contentStream.close();

                newDocument.save(outputDir + "/page_" + pageNum + ".pdf");
                newDocument.close();
                pageNum++;
            }
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}