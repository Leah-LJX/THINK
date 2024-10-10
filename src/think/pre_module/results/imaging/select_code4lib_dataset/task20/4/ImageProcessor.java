import org.apache.commons.imaging.common.ImageBuilder;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.apache.commons.imaging.formats.jpeg.JpegImageParser;
import org.apache.commons.imaging.formats.tiff.TiffImageParser;
import org.apache.commons.imaging.palette.PaletteFactory;
import org.apache.commons.imaging.palette.SimplePalette;
import org.apache.commons.imaging.palette.SimplePaletteFactory;
import org.apache.commons.imaging.palette.SimplePaletteFactory.SimplePalette;
import org.apache.commons.imaging.palette.Palette;
import org.apache.commons.imaging.palette.ColorCount;
import org.apache.commons.imaging.palette.MedianCut;
import org.apache.commons.imaging.palette.PaletteFactory;
import org.apache.commons.imaging.palette.PaletteFactory.MedianCut;
import org.apache.commons.imaging.palette.PaletteFactory.Palette;
import org.apache.commons.imaging.palette.PaletteFactory.ColorCount;
import org.apache.commons.imaging.palette.PaletteFactory.process;
import org.apache.commons.imaging.palette.PaletteFactory.applyFloydSteinbergDithering;
import org.apache.commons.imaging.palette.PaletteFactory.groupColors;
import org.apache.commons.imaging.palette.PaletteFactory.convertBetweenColorSpacesX2;
import org.apache.commons.imaging.palette.PaletteFactory.convertBetweenICCProfiles;
import org.apache.commons.imaging.palette.PaletteFactory.collectRawImageData;
import org.apache.commons.imaging.palette.PaletteFactory.convertFromColorSpace;
import org.apache.commons.imaging.palette.PaletteFactory.convertToColorSpace;
import org.apache.commons.imaging.palette.PaletteFactory.convertToICCProfile;
import org.apache.commons.imaging.palette.PaletteFactory.ImageInfo;
import org.apache.commons.imaging.palette.PaletteFactory.DataReaderStrips;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {
    public static void applySepiaToneFilterBatch(String[] imagePaths, String outputPath, double intensityFactor) {
        for (String imagePath : imagePaths) {
            try {
                BufferedImage image = loadImage(imagePath);
                applySepiaToneFilter(image, intensityFactor);
                saveImage(image, outputPath + File.separator + new File(imagePath).getName());
            } catch (IOException e) {
                System.out.println("Error processing image: " + imagePath);
            }
        }
    }

    public static BufferedImage loadImage(String imagePath) throws IOException {
        File file = new File(imagePath);
        return Imaging.getBufferedImage(file);
    }

    public static void saveImage(BufferedImage image, String outputPath) throws IOException {
        File file = new File(outputPath);
        Imaging.writeImage(image, file, ImageFormats.PNG, null);
    }

    public static void applySepiaToneFilter(BufferedImage image, double intensityFactor) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);

                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                int tr = (int) (0.393 * r + 0.769 * g + 0.189 * b);
                int tg = (int) (0.349 * r + 0.686 * g + 0.168 * b);
                int tb = (int) (0.272 * r + 0.534 * g + 0.131 * b);

                r = Math.min(255, (int) (tr * intensityFactor));
                g = Math.min(255, (int) (tg * intensityFactor));
                b = Math.min(255, (int) (tb * intensityFactor));

                int newRgb = (r << 16) | (g << 8) | b;
                image.setRGB(x, y, newRgb);
            }
        }
    }
}