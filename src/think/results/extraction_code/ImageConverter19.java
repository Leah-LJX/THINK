import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.Imaging;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class ImageConverter19 {

    public static void convertToBlackAndWhiteWithDithering(String imagePath, String outputPath) throws IOException {
        // Load the image
        File inputFile = new File(imagePath);
        BufferedImage bufferedImage = Imaging.getBufferedImage(inputFile);

        // Convert to grayscale
        BufferedImage grayImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                int rgb = bufferedImage.getRGB(x, y);
                int gray = (int) (0.3 * ((rgb >> 16) & 0xFF) + 0.59 * ((rgb >> 8) & 0xFF) + 0.11 * (rgb & 0xFF));
                int newRgb = (gray << 16) + (gray << 8) + gray;
                grayImage.setRGB(x, y, newRgb);
            }
        }

        // Apply dithering
        BufferedImage ditheredImage = applyFloydSteinbergDithering(grayImage);

        // Save the dithered image
        File outputFile = new File(outputPath);
        Imaging.writeImage(ditheredImage, outputFile, ImageFormats.PNG);
    }

    public static BufferedImage applyFloydSteinbergDithering(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();

        int[][] pixel = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixel[y][x] = raster.getSample(x, y, 0);
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int oldPixel = pixel[y][x];
                int newPixel = oldPixel > 127 ? 255 : 0;
                int error = oldPixel - newPixel;
                raster.setSample(x, y, 0, newPixel);

                if (x + 1 < width) {
                    pixel[y][x + 1] += error * 7 / 16;
                }
                if (x - 1 >= 0 && y + 1 < height) {
                    pixel[y + 1][x - 1] += error * 3 / 16;
                }
                if (y + 1 < height) {
                    pixel[y + 1][x] += error * 5 / 16;
                }
                if (x + 1 < width && y + 1 < height) {
                    pixel[y + 1][x + 1] += error * 1 / 16;
                }
            }
        }
        return image;
    }

    public static void main(String[] args) {
        try {
            convertToBlackAndWhiteWithDithering("path/to/input/image.jpg", "path/to/output/image.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}