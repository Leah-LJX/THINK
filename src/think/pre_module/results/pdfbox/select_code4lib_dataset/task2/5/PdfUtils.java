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
            public RandomAccessRead createFor(RandomAccessRead source) throws IOException {
                return new RandomAccessStreamCache(source);
            }
        };

        for (String pdfPath : pdfPaths) {
            merger.addSource(pdfPath);
        }

        try {
            File outputFile = new File(outputPath);
            OutputStream outputStream = new java.io.FileOutputStream(outputFile);
            ExternalSigningSupport externalSigningSupport = merger.saveIncrementalForExternalSigning(outputStream);

            PDDocument document = externalSigningSupport.getDocument();
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                PDPage page = document.getPage(i);
                PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 36);
                contentStream.beginText();
                contentStream.setNonStrokingColor(200, 200, 200);
                contentStream.setTextMatrix(PDRectangle.A4.getLowerLeftX(), PDRectangle.A4.getLowerLeftY());
                contentStream.showText(watermarkText);
                contentStream.endText();
                contentStream.close();
            }

            document.saveIncremental(outputStream);
            document.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}