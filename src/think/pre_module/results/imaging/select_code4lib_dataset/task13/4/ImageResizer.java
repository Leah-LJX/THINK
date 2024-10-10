import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.ImageMetadata.ImageInfo;
import org.apache.commons.imaging.common.ImageMetadata.ImageInfo.ColorType;
import org.apache.commons.imaging.common.ImageMetadata.ImageInfo.CompressionAlgorithm;
import org.apache.commons.imaging.common.ImageMetadata.ImageFormat;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;
import org.apache.commons.imaging.palette.Palette;
import org.apache.commons.imaging.palette.PaletteFactory.MedianCut;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ImageResizer {
    public static void batchResizeImages(String[] imagePaths, String outputPath, int maxWidth, int maxHeight) {
        for (String imagePath : imagePaths) {
            try {
                File imageFile = new File(imagePath);
                BufferedImage image = Imaging.getBufferedImage(imageFile);

                Dimension newSize = calculateNewSize(image.getWidth(), image.getHeight(), maxWidth, maxHeight);
                BufferedImage resizedImage = resizeImage(image, newSize.width, newSize.height);

                String outputFileName = outputPath + File.separator + imageFile.getName();
                File outputFile = new File(outputFileName);
                Imaging.writeImage(resizedImage, outputFile, ImageFormat.IMAGE_FORMAT_JPEG, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Dimension calculateNewSize(int originalWidth, int originalHeight, int maxWidth, int maxHeight) {
        if (originalWidth <= maxWidth && originalHeight <= maxHeight) {
            return new Dimension(originalWidth, originalHeight);
        }

        double widthRatio = (double) maxWidth / originalWidth;
        double heightRatio = (double) maxHeight / originalHeight;
        double ratio = Math.min(widthRatio, heightRatio);

        int newWidth = (int) (originalWidth * ratio);
        int newHeight = (int) (originalHeight * ratio);

        return new Dimension(newWidth, newHeight);
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int newWidth, int newHeight) {
        return Imaging.getBufferedImage(Imaging.getBufferedImage(originalImage.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH)));
    }
}