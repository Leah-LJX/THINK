import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.ImagingException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
public class ImageConverter {

    public static void batchConvertImageFormats(String[] inputPaths, String outputPath, String outputFormat, float quality) {
        for (String inputPath : inputPaths) {
            File inputFile = new File(inputPath);
            File outputFile = new File(outputPath, getOutputFileName(inputFile.getName(), outputFormat));

            try {
                BufferedImage image = Imaging.getBufferedImage(inputFile);
                saveImageWithQuality(image, outputFile, outputFormat, quality);
            } catch (ImagingException | IOException e) {
                System.err.println("Error processing file " + inputFile.getName() + ": " + e.getMessage());
            }
        }
    }

    public static String getOutputFileName(String inputFileName, String outputFormat) {
        int dotIndex = inputFileName.lastIndexOf('.');
        String baseName = (dotIndex == -1) ? inputFileName : inputFileName.substring(0, dotIndex);
        return baseName + "." + outputFormat.toLowerCase();
    }

    public static void saveImageWithQuality(BufferedImage image, File outputFile, String outputFormat, float quality) throws IOException {
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(outputFormat);
        if (!writers.hasNext()) {
            throw new IllegalArgumentException("No writer found for format: " + outputFormat);
        }

        ImageWriter writer = writers.next();
        try (ImageOutputStream ios = ImageIO.createImageOutputStream(outputFile)) {
            writer.setOutput(ios);

            ImageWriteParam param = writer.getDefaultWriteParam();
            if (param.canWriteCompressed()) {
                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                param.setCompressionQuality(quality);
            }

            writer.write(null, new IIOImage(image, null, null), param);
        } finally {
            writer.dispose();
        }
    }

    public static void main(String[] args) {
        String[] inputPaths = {"path/to/image1.jpg", "path/to/image2.png"};
        String outputPath = "path/to/output/directory";
        String outputFormat = "png";  // e.g., "jpg", "png"
        float quality = 0.8f;  // Adjust the quality (0.0f to 1.0f)

        batchConvertImageFormats(inputPaths, outputPath, outputFormat, quality);
    }
}