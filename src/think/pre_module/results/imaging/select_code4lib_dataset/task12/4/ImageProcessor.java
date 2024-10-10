import org.apache.commons.imaging.common.ImageBuilder;
import org.apache.commons.imaging.common.ImageReadException;
import org.apache.commons.imaging.common.ImageWriteException;
import org.apache.commons.imaging.formats.jpeg.JpegImageParser;
import org.apache.commons.imaging.palette.PaletteFactory;
import org.apache.commons.imaging.palette.PaletteFactory.MedianCut;
import org.apache.commons.imaging.palette.PaletteFactory.QuantizationMethod;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {

    public static void extractRgbChannels(String imagePath, String outputDir) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));

            // Separate the RGB channels
            BufferedImage redChannel = extractChannel(image, 0);
            BufferedImage greenChannel = extractChannel(image, 1);
            BufferedImage blueChannel = extractChannel(image, 2);

            // Save each channel as a new image
            ImageIO.write(redChannel, "png", new File(outputDir + "/red_channel.png"));
            ImageIO.write(greenChannel, "png", new File(outputDir + "/green_channel.png"));
            ImageIO.write(blueChannel, "png", new File(outputDir + "/blue_channel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage extractChannel(BufferedImage image, int channelIndex) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage channelImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = image.getRGB(x, y);
                int alpha = (argb >> 24) & 0xFF;
                int red = (argb >> 16) & 0xFF;
                int green = (argb >> 8) & 0xFF;
                int blue = argb & 0xFF;

                // Set the specified channel to the pixel value
                int newArgb = alpha << 24;
                if (channelIndex == 0) {
                    newArgb |= (red << 16);
                } else if (channelIndex == 1) {
                    newArgb |= (green << 8);
                } else {
                    newArgb |= blue;
                }

                channelImage.setRGB(x, y, newArgb);
            }
        }

        return channelImage;
    }
}