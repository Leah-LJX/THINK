import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ByteSource;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;

import java.io.*;

public class ImageRecovery {

    public static void recoverAndRepairImages(String[] imagePaths, String outputPath) {
        for (String imagePath : imagePaths) {
            try {
                byte[] imageBytes = readImageBytes(imagePath);
                TiffOutputSet outputSet = Imaging.getExifMetadata(imageBytes);
                if (outputSet != null) {
                    writeImageBytes(outputPath, imageBytes, outputSet);
                }
            } catch (IOException | ImageReadException | ImageWriteException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] readImageBytes(String imagePath) throws IOException {
        File file = new File(imagePath);
        try (InputStream is = new FileInputStream(file)) {
            return Imaging.getBufferedImage(file);
        }
    }

    public static void writeImageBytes(String outputPath, byte[] imageBytes, TiffOutputSet outputSet) throws IOException, ImageWriteException {
        File outputFile = new File(outputPath);
        try (OutputStream os = new FileOutputStream(outputFile)) {
            Imaging.writeImage(outputSet, os, imageBytes, Imaging.guessFormat(imageBytes));
        }
    }
}