import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.io.RandomAccessStreamCache;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;

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
                return new RandomAccessFile(new File(outputPath), "rw");
            }
        };

        for (String pdfPath : pdfPaths) {
            merger.addSource(pdfPath);
        }

        try {
            PDDocument mergedDocument = merger.mergeDocuments(streamCacheCreateFunction);

            PDPageTree pages = mergedDocument.getPages();
            for (PDPage page : pages) {
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

            mergedDocument.saveIncrementalForExternalSigning(new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    // Not used
                }
            });

            mergedDocument.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}