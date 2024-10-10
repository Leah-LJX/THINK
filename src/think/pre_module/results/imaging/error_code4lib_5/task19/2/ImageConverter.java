import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageBuilder;
import org.apache.commons.imaging.common.PaletteFactory;
import org.apache.commons.imaging.palette.Palette;
import org.apache.commons.imaging.palette.PaletteFactory;
import org.apache.commons.imaging.palette.SimplePalette;
import org.apache.commons.imaging.palette.SimplePaletteFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageConverter {
    public static void convertToBlackAndWhiteWithDithering(String imagePath, String outputPath) {
        try {
            File inputFile = new File(imagePath);
            BufferedImage image = Imaging.getBufferedImage(inputFile);

            // Create a grayscale palette
            PaletteFactory paletteFactory = new SimplePaletteFactory();
            Palette grayscalePalette = paletteFactory.makeExactGrayscalePalette(2);

            // Apply Floyd-Steinberg dithering to convert the image to black and white
            ImageBuilder imageBuilder = new ImageBuilder(image);
            imageBuilder.ditherImage(grayscalePalette);

            // Save the converted image
            File outputFile = new File(outputPath);
            Imaging.writeImage(imageBuilder.getBufferedImage(), outputFile, ImageInfo.IMAGE_INFO_UNKNOWN);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}