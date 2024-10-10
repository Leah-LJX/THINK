import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

public class PdfSplitter {

    public static void splitPdfAndAddHeaderFooter(String pdfFilePath, String outputDir, String headerText, String footerText) {
        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
            int pageCount = document.getNumberOfPages();
            
            for (int i = 0; i < pageCount; i++) {
                PDDocument newDocument = new PDDocument();
                PDPage page = document.getPage(i);
                newDocument.addPage(page);

                addHeaderFooter(newDocument, headerText, footerText);

                newDocument.save(outputDir + File.separator + "page_" + (i + 1) + ".pdf");
                newDocument.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addHeaderFooter(PDDocument document, String headerText, String footerText) throws IOException {
        PDPage page = document.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.newLineAtOffset(50, page.getMediaBox().getHeight() - 20); // Header position
        contentStream.showText(headerText);
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.newLineAtOffset(50, 20); // Footer position
        contentStream.showText(footerText);
        contentStream.endText();

        contentStream.close();
    }

    public static void main(String[] args) {
        String pdfFilePath = "input.pdf";
        String outputDir = "output";
        String headerText = "Header";
        String footerText = "Footer";

        splitPdfAndAddHeaderFooter(pdfFilePath, outputDir, headerText, footerText);
    }
}