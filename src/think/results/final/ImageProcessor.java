import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.Imaging;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {

    public static void applyConvolutionFilter(String imagePath, String outputPath, double[][] filterMatrix) throws ImageReadException , ImageWriteException {
        try {
            // Load the image
            File inputFile = new File(imagePath);
            BufferedImage image = Imaging.getBufferedImage(inputFile);
            int width = image.getWidth();
            int height = image.getHeight();
            
            // Prepare the output image
            BufferedImage outputImage = new BufferedImage(width, height, image.getType());
            
            // Apply convolution filter
            int filterWidth = filterMatrix.length;
            int filterHeight = filterMatrix[0].length;
            int filterOffsetX = filterWidth / 2;
            int filterOffsetY = filterHeight / 2;

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    double redSum = 0.0;
                    double greenSum = 0.0;
                    double blueSum = 0.0;

                    for (int filterY = 0; filterY < filterHeight; filterY++) {
                        for (int filterX = 0; filterX < filterWidth; filterX++) {
                            int imageX = Math.min(Math.max(x + filterX - filterOffsetX, 0), width - 1);
                            int imageY = Math.min(Math.max(y + filterY - filterOffsetY, 0), height - 1);
                            
                            Color color = new Color(image.getRGB(imageX, imageY));
                            
                            redSum += color.getRed() * filterMatrix[filterY][filterX];
                            greenSum += color.getGreen() * filterMatrix[filterY][filterX];
                            blueSum += color.getBlue() * filterMatrix[filterY][filterX];
                        }
                    }

                    int red = Math.min(Math.max((int) redSum, 0), 255);
                    int green = Math.min(Math.max((int) greenSum, 0), 255);
                    int blue = Math.min(Math.max((int) blueSum, 0), 255);

                    outputImage.setRGB(x, y, new Color(red, green, blue).getRGB());
                }
            }

            // Save the output image
            File outputFile = new File(outputPath);
            Imaging.writeImage(outputImage, outputFile, ImageFormats.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage
        double[][] filterMatrix = {
            { -1, -1, -1 },
            { -1,  8, -1 },
            { -1, -1, -1 }
        };
        
        applyConvolutionFilter("input.png", "output.png", filterMatrix);
    }
}