import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class ImageProcessor12 {

    public static void extractRgbChannels(String imagePath, String outputDir) throws ImageReadException {
        try {
            // Load the image
            File imageFile = new File(imagePath);
            BufferedImage image = Imaging.getBufferedImage(imageFile);

            // Get image dimensions
            int width = image.getWidth();
            int height = image.getHeight();

            // Create BufferedImage objects for each channel
            BufferedImage redImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            BufferedImage greenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            BufferedImage blueImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            // Iterate over each pixel and separate the channels
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int rgb = image.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;

                    // Set red channel pixel
                    int redPixel = (red << 16);
                    redImage.setRGB(x, y, redPixel);

                    // Set green channel pixel
                    int greenPixel = (green << 8);
                    greenImage.setRGB(x, y, greenPixel);

                    // Set blue channel pixel
                    int bluePixel = blue;
                    blueImage.setRGB(x, y, bluePixel);
                }
            }

            // Save each channel image
            File redOutputFile = new File(outputDir, "red_channel.png");
            File greenOutputFile = new File(outputDir, "green_channel.png");
            File blueOutputFile = new File(outputDir, "blue_channel.png");

            ImageIO.write(redImage, "png", redOutputFile);
            ImageIO.write(greenImage, "png", greenOutputFile);
            ImageIO.write(blueImage, "png", blueOutputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String imagePath = "path_to_your_image";
        String outputDir = "path_to_output_directory";
        extractRgbChannels(imagePath, outputDir);
    }
}