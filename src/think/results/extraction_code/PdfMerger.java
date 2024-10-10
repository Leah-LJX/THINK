import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

public class PdfMerger {

    public static void mergePdfFilesWithWatermark(String[] pdfPaths, String outputPath, String watermarkText) {
        PDFMergerUtility pdfMerger = new PDFMergerUtility();
        PDDocument mergedDocument = new PDDocument();

        try {
            // Add source files to the merger utility
            for (String pdfPath : pdfPaths) {
                PDDocument document = PDDocument.load(new File(pdfPath));
                pdfMerger.addSource(pdfPath);

                // Add watermark to each page in the current document
                for (PDPage page : document.getPages()) {
                    addWatermark(page, watermarkText, document);
                }

                // Append pages to the merged document
                for (int i = 0; i < document.getNumberOfPages(); i++) {
                    mergedDocument.addPage(document.getPage(i));
                }

                // Clean up
                document.close();
            }

            // Save the merged document
            mergedDocument.save(outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                mergedDocument.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addWatermark(PDPage page, String watermarkText, PDDocument document) throws IOException {
        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 72);
        contentStream.setNonStrokingColor(200, 200, 200); // Light gray color
        contentStream.setTextMatrix(Matrix.getRotateInstance(Math.toRadians(45), 100, 100));
        contentStream.newLineAtOffset(100, 100);
        contentStream.showText(watermarkText);
        contentStream.endText();

        contentStream.close();
    }

    public static void main(String[] args) {
        String[] pdfPaths = {"input1.pdf", "input2.pdf", "input3.pdf"};
        String outputPath = "merged_output.pdf";
        String watermarkText = "WATERMARK";

        mergePdfFilesWithWatermark(pdfPaths, outputPath, watermarkText);
    }
}