import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.ImagingException;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class ImageAutoCropper {

    public static void autoCropImages(String[] imagePaths, String outputPath) {
        for (String imagePath : imagePaths) {
            try {
                File inputFile = new File(imagePath);
                BufferedImage image = Imaging.getBufferedImage(inputFile);

                // Find cropping boundaries
                int left = findLeftBorder(image);
                int right = findRightBorder(image);
                int top = findTopBorder(image);
                int bottom = findBottomBorder(image);

                // Crop the image
                BufferedImage croppedImage = image.getSubimage(left, top, right - left + 1, bottom - top + 1);

                // Save the cropped image
                File outputFile = new File(outputPath, inputFile.getName());
                Imaging.writeImage(croppedImage, outputFile, ImageFormats.PNG);

            } catch (IOException | ImagingException e) {
                e.printStackTrace();
            }
        }
    }

    public static int findLeftBorder(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Color borderColor = new Color(image.getRGB(0, 0));

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (!new Color(image.getRGB(x, y)).equals(borderColor)) {
                    return x;
                }
            }
        }
        return 0; // Default to 0 if the whole image is the border color
    }

    public static int findRightBorder(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Color borderColor = new Color(image.getRGB(0, 0));

        for (int x = width - 1; x >= 0; x--) {
            for (int y = 0; y < height; y++) {
                if (!new Color(image.getRGB(x, y)).equals(borderColor)) {
                    return x;
                }
            }
        }
        return width - 1; // Default to width-1 if the whole image is the border color
    }

    public static int findTopBorder(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Color borderColor = new Color(image.getRGB(0, 0));

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (!new Color(image.getRGB(x, y)).equals(borderColor)) {
                    return y;
                }
            }
        }
        return 0; // Default to 0 if the whole image is the border color
    }

    public static int findBottomBorder(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Color borderColor = new Color(image.getRGB(0, 0));

        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                if (!new Color(image.getRGB(x, y)).equals(borderColor)) {
                    return y;
                }
            }
        }
        return height - 1; // Default to height-1 if the whole image is the border color
    }

    public static void main(String[] args) {
        // Example usage
        String[] imagePaths = {"path/to/input/image1.jpg", "path/to/input/image2.jpg"};
        String outputPath = "path/to/output/";

        autoCropImages(imagePaths, outputPath);
    }
}