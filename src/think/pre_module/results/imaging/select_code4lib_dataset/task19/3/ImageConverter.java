import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageFormats;
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

            // Convert the image to black and white using a dithering algorithm
            ImageBuilder imageBuilder = new ImageBuilder(image);
            PaletteFactory paletteFactory = new PaletteFactory();
            paletteFactory.makePalette(image, 2); // Create a black and white palette
            imageBuilder = imageBuilder.ditherImage(paletteFactory.getPalette(), ImageFormats.PNG, null); // Apply dithering

            // Save the black and white image to the output path
            File outputFile = new File(outputPath);
            Imaging.writeImage(imageBuilder.getBufferedImage(), outputFile, ImageFormats.PNG, null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}