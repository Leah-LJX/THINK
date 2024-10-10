import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.io.RandomAccessStreamCache;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class PdfUtils {

    public static void mergePdfFilesWithWatermark(String[] pdfPaths, String outputPath, String watermarkText) {
        PDFMergerUtility merger = new PDFMergerUtility();
        RandomAccessStreamCache.StreamCacheCreateFunction streamCacheCreateFunction = new RandomAccessStreamCache.StreamCacheCreateFunction() {
            @Override
            public RandomAccessRead createForRandomAccess() throws IOException {
                return null; // Implement the creation of RandomAccessRead
            }
        };

        for (String pdfPath : pdfPaths) {
            merger.addSource(pdfPath);
        }

        try {
            PDDocument mergedDocument = new PDDocument();
            List<RandomAccessRead> sourcesList = merger.getSourcesList();
            for (RandomAccessRead source : sourcesList) {
                PDDocument sourceDocument = PDDocument.load(source);
                for (int i = 0; i < sourceDocument.getNumberOfPages(); i++) {
                    PDPage page = sourceDocument.getPage(i);
                    PDPageContentStream contentStream = new PDPageContentStream(sourceDocument, page, PDPageContentStream.AppendMode.APPEND, true, true);
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 36);
                    contentStream.setLeading(14.5f);
                    contentStream.newLineAtOffset(100, 700);
                    contentStream.showText(watermarkText);
                    contentStream.endText();
                    contentStream.close();
                }
                mergedDocument.addPage(page);
                sourceDocument.close();
            }
            mergedDocument.save(outputPath);
            mergedDocument.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}