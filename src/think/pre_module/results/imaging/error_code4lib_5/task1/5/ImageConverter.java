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
                    if (outputFormat.equalsIgnoreCase("pdf")) {
                        convertToPdf(image, outputPath, quality);
                    } else {
                        convertToImage(image, outputPath, outputFormat, quality);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void convertToPdf(BufferedImage image, String outputPath, float quality) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDImageXObject pdfImage = PDImageXObject.createFromImage(image, document, quality);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.drawImage(pdfImage, 0, 0);
        contentStream.close();

        document.save(outputPath);
        document.close();
    }

    public static void convertToImage(BufferedImage image, String outputPath, String outputFormat, float quality) throws IOException {
        File outputFile = new File(outputPath);
        Imaging.writeImage(image, outputFile, outputFormat, null);
    }
}