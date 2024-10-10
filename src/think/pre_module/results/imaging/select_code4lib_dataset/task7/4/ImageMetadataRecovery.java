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
                TiffOutputSet outputSet = recoverMetadata(imageBytes);
                if (outputSet != null) {
                    repairImage(imageBytes, outputPath, outputSet);
                }
            } catch (IOException | ImageReadException | ImageWriteException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] readImageBytes(String imagePath) throws IOException {
        File imageFile = new File(imagePath);
        try (InputStream is = new FileInputStream(imageFile)) {
            return Imaging.getBufferedImage(imageFile);
        }
    }

    public static TiffOutputSet recoverMetadata(byte[] imageBytes) throws ImageReadException, IOException {
        ByteSource byteSource = new ByteSource.ByteSourceArray(imageBytes);
        return Imaging.getMetadata(byteSource);
    }

    public static void repairImage(byte[] imageBytes, String outputPath, TiffOutputSet outputSet) throws IOException, ImageWriteException {
        try (OutputStream os = new FileOutputStream(outputPath)) {
            Imaging.writeImage(outputSet, os, imageBytes, Imaging.guessFormat(imageBytes), null);
        }
    }
}