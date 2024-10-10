import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ByteSource;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;

import java.io.*;

public class ImageMetadataRecovery {

    public static void recoverAndRepairImages(String[] imagePaths, String outputPath) {
        for (String imagePath : imagePaths) {
            try {
                byte[] imageBytes = readImageBytes(imagePath);
                TiffOutputSet outputSet = Imaging.getExifMetadata(imageBytes);
                if (outputSet != null) {
                    writeImageWithRecoveredMetadata(imageBytes, outputPath, outputSet);
                } else {
                    System.out.println("No metadata found for image: " + imagePath);
                }
            } catch (IOException | ImageReadException | ImageWriteException e) {
                System.out.println("Error processing image: " + imagePath + " - " + e.getMessage());
            }
        }
    }

    public static byte[] readImageBytes(String imagePath) throws IOException {
        File imageFile = new File(imagePath);
        try (InputStream is = new FileInputStream(imageFile)) {
            return Imaging.getBufferedImage(imageFile);
        }
    }

    public static void writeImageWithRecoveredMetadata(byte[] imageBytes, String outputPath, TiffOutputSet outputSet)
            throws IOException, ImageWriteException {
        File outputFile = new File(outputPath);
        try (OutputStream os = new FileOutputStream(outputFile)) {
            Imaging.writeImage(outputSet, os, imageBytes, Imaging.getImageFormat(imageBytes));
        }
    }
}