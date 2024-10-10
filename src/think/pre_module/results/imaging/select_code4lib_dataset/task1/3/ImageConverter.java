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
                byte[] inputBytes = org.apache.commons.io.FileUtils.readFileToByteArray(inputFile);
                String inputMimeType = org.apache.commons.io.FilenameUtils.getExtension(inputPath);

                BufferedImage inputImage = Imaging.getBufferedImage(inputBytes);

                if (inputImage != null) {
                    BufferedImage outputImage = convertImageFormat(inputImage, outputFormat, quality);
                    saveImage(outputImage, outputPath + "/" + inputFile.getName(), outputFormat);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static BufferedImage convertImageFormat(BufferedImage inputImage, String outputFormat, float quality) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            PDImageXObject pdfImage = org.apache.pdfbox.pdmodel.PDImageXObject.createFromImage(inputImage, document, quality);
            contentStream.drawImage(pdfImage, 0, 0);
            contentStream.close();

            File tempFile = File.createTempFile("temp", "." + outputFormat);
            document.save(tempFile);
            document.close();

            return Imaging.getBufferedImage(org.apache.commons.io.FileUtils.readFileToByteArray(tempFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void saveImage(BufferedImage image, String outputPath, String outputFormat) {
        try {
            File outputFile = new File(outputPath);
            Imaging.writeImage(image, outputFile, outputFormat, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}