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
                BufferedImage inputImage = Imaging.getBufferedImage(inputBytes);

                // Convert the image to the desired format
                BufferedImage outputImage = convertImageFormat(inputImage, outputFormat, quality);

                // Save the output image to the specified output path
                File outputFile = new File(outputPath + File.separator + inputFile.getName() + "." + outputFormat);
                Imaging.writeImage(outputImage, outputFile, outputFormat, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static BufferedImage convertImageFormat(BufferedImage inputImage, String outputFormat, float quality) {
        // Convert the image to the desired format using the createFromImage method
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            PDImageXObject pdImage = org.apache.pdfbox.pdmodel.PDImageXObject.createFromImage(document, inputImage, quality);
            contentStream.drawImage(pdImage, 0, 0);
            contentStream.close();
            document.save("temp.pdf");
            document.close();

            byte[] pdfBytes = org.apache.commons.io.FileUtils.readFileToByteArray(new File("temp.pdf"));
            return Imaging.getBufferedImage(pdfBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}