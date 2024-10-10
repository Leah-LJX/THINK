import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.io.RandomAccessStreamCache;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
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
            public RandomAccessRead createFor(RandomAccessFile file) throws IOException {
                return new RandomAccessFile(file);
            }
        };

        for (String pdfPath : pdfPaths) {
            merger.addSource(pdfPath);
        }

        try {
            File outputFile = new File(outputPath);
            PDDocument mergedDocument = merger.mergeDocuments(streamCacheCreateFunction);

            PDPageTree pages = mergedDocument.getPages();
            for (int i = 0; i < pages.getCount(); i++) {
                PDPage page = pages.get(i);
                PDRectangle pageSize = page.getMediaBox();
                float x = (pageSize.getLowerLeftX() + pageSize.getUpperRightX()) / 2;
                float y = (pageSize.getLowerLeftY() + pageSize.getUpperRightY()) / 2;

                PDPageContentStream contentStream = new PDPageContentStream(mergedDocument, page, PDPageContentStream.AppendMode.APPEND, true, true);
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.newLineAtOffset(x, y);
                contentStream.showText(watermarkText);
                contentStream.endText();
                contentStream.close();
            }

            mergedDocument.save(outputFile);
            mergedDocument.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}