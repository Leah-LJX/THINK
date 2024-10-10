import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageBuilder;
import org.apache.commons.imaging.palette.PaletteFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageConverter {
    public static void convertToBlackAndWhiteWithDithering(String imagePath, String outputPath) {
        try {
            File inputFile = new File(imagePath);
            BufferedImage image = Imaging.getBufferedImage(inputFile);

            // Convert the image to grayscale
            BufferedImage grayscaleImage = convertToGrayscale(image);

            // Apply Floyd-Steinberg dithering to preserve image quality
            PaletteFactory paletteFactory = new PaletteFactory();
            BufferedImage ditheredImage = paletteFactory.applyFloydSteinbergDithering(grayscaleImage);

            // Save the dithered black and white image
            File outputFile = new File(outputPath);
            Imaging.writeImage(ditheredImage, outputFile, ImageInfo.IMAGE_INFO_UNKNOWN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage convertToGrayscale(BufferedImage image) {
        // Convert the image to grayscale using a color space conversion
        return new ImageBuilder(image).convertToGrayscale().getBufferedImage();
    }
}