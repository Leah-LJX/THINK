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
                    // Attempt to repair the image by updating the metadata
                    updateExifMetadataLossless(imageBytes, outputPath, outputSet);
                } else {
                    // If metadata cannot be recovered, simply copy the image to the output path
                    copyImageFile(imagePath, outputPath);
                }
            } catch (IOException | ImageReadException | ImageWriteException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] readImageBytes(String imagePath) throws IOException {
        File imageFile = new File(imagePath);
        try (FileInputStream fis = new FileInputStream(imageFile)) {
            return fis.readAllBytes();
        }
    }

    public static void updateExifMetadataLossless(byte[] src, String outputPath, TiffOutputSet outputSet)
            throws IOException, ImageWriteException {
        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            Imaging.writeImage(outputSet, fos, src, Imaging.getImageFormat(src), null);
        }
    }

    public static void copyImageFile(String sourcePath, String outputPath) throws IOException {
        File sourceFile = new File(sourcePath);
        File outputFile = new File(outputPath);
        try (InputStream is = new FileInputStream(sourceFile);
             OutputStream os = new FileOutputStream(outputFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }
}