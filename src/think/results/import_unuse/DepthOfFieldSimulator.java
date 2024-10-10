import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.Imaging;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class DepthOfFieldSimulator {

    public static void simulateDepthOfField(String imagePath, String outputPath, int focusStart, int focusEnd) throws IOException , ImageReadException , ImageWriteException {
        // Load the image
        BufferedImage image = Imaging.getBufferedImage(new File(imagePath));

        // Create a blurred version of the image
        BufferedImage blurredImage = blurImage(image);

        // Create the final image combining focused and blurred parts
        BufferedImage finalImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = finalImage.getGraphics();

        for (int y = 0; y < image.getHeight(); y++) {
            if (y >= focusStart && y <= focusEnd) {
                // Draw focused area
                g.drawImage(image, 0, y, image.getWidth(), y + 1, 0, y, image.getWidth(), y + 1, null);
            } else {
                // Draw blurred area
                g.drawImage(blurredImage, 0, y, image.getWidth(), y + 1, 0, y, image.getWidth(), y + 1, null);
            }
        }
        g.dispose();

        // Save the final image
        Imaging.writeImage(finalImage, new File(outputPath), ImageFormats.PNG);
    }

    public static BufferedImage blurImage(BufferedImage image) {
        // Simple box blur implementation
        int radius = 5; // Radius of the blur
        BufferedImage blurred = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = radius; y < image.getHeight() - radius; y++) {
            for (int x = radius; x < image.getWidth() - radius; x++) {
                int r = 0, g = 0, b = 0;
                int count = 0;
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        int rgb = image.getRGB(x + dx, y + dy);
                        r += (rgb >> 16) & 0xFF;
                        g += (rgb >> 8) & 0xFF;
                        b += rgb & 0xFF;
                        count++;
                    }
                }
                r /= count;
                g /= count;
                b /= count;
                int rgb = (r << 16) | (g << 8) | b;
                blurred.setRGB(x, y, rgb);
            }
        }
        return blurred;
    }

    public static void main(String[] args) {
        try {
            simulateDepthOfField("input.png", "output.png", 100, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}