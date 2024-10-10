import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PDFConverter {

    public static void convertPdfToImages(String pdfFilePath, String imagesOutputDir, int resolution) {
        try {
            PDDocument document = PDDocument.load(new File(pdfFilePath));
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, resolution, ImageType.RGB);
                String imageFileName = imagesOutputDir + "/page_" + (page + 1) + ".png";
                ImageIO.write(bim, "png", new File(imageFileName));
            }
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}