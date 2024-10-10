import org.apache.commons.imaging.Imaging;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageConverter {
    public static void batchConvertImageFormats(String[] inputPaths, String outputPath, String outputFormat, float quality) {
        for (String inputPath : inputPaths) {
            try {
                File inputFile = new File(inputPath);
                BufferedImage image = Imaging.getBufferedImage(inputFile);
                if (image != null) {
                    String outputFileName = outputPath + File.separator + inputFile.getName().replaceFirst("[.][^.]+$", "") + "." + outputFormat;
                    File outputFile = new File(outputFileName);
                    PDImageXObject pdImage = Imaging.createFromImage(image, quality);
                    PDDocument document = new PDDocument();
                    PDPage page = new PDPage();
                    document.addPage(page);
                    PDPageContentStream contentStream = new PDPageContentStream(document, page);
                    contentStream.drawImage(pdImage, 20, 20);
                    contentStream.close();
                    document.save(outputFile);
                    document.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}