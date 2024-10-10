import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PDFRedactor {

    public static void redactKeywordsInPdf(String pdfFilePath, String outputPdfPath, String[] keywords) {
        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
            PDFTextStripper stripper = new PDFTextStripper() {
                @Override
                protected void writeString(String string, List<TextPosition> textPositions) throws IOException {
                    for (String keyword : keywords) {
                        if (string.contains(keyword)) {
                            for (TextPosition text : textPositions) {
                                if (string.contains(keyword)) {
                                    float x = text.getXDirAdj();
                                    float y = text.getYDirAdj();
                                    float width = text.getWidthDirAdj();
                                    float height = text.getHeightDir();
                                    PDPage page = getCurrentPage();
                                    try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true)) {
                                        contentStream.setNonStrokingColor(0, 0, 0); // black color
                                        contentStream.addRect(x, y - height, width, height);
                                        contentStream.fill();
                                    }
                                }
                            }
                        }
                    }
                    super.writeString(string, textPositions);
                }
            };

            // Process all pages
            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                stripper.setStartPage(page + 1);
                stripper.setEndPage(page + 1);
                stripper.getText(document);
            }

            // Save the redacted PDF
            document.save(outputPdfPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String pdfFilePath = "input.pdf";
        String outputPdfPath = "output_redacted.pdf";
        String[] keywords = {"keyword1", "keyword2", "keyword3"};
        redactKeywordsInPdf(pdfFilePath, outputPdfPath, keywords);
    }
}