import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.Imaging;

public class ImageResizer {

    public static void batchResizeImages(String[] imagePaths, String outputPath, int maxWidth, int maxHeight) throws ImageReadException , ImageWriteException {
        for (String imagePath : imagePaths) {
            try {
                // Read the image from the file
                BufferedImage originalImage = Imaging.getBufferedImage(new File(imagePath));

                // Compute new dimensions while preserving aspect ratio
                Dimension newSize = getScaledDimension(
                        new Dimension(originalImage.getWidth(), originalImage.getHeight()), 
                        new Dimension(maxWidth, maxHeight));

                // Resize the image
                BufferedImage resizedImage = new BufferedImage(newSize.width, newSize.height, originalImage.getType());
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(originalImage, 0, 0, newSize.width, newSize.height, null);
                g.dispose();

                // Save the resized image to the output path
                File outputDir = new File(outputPath);
                if (!outputDir.exists()) {
                    outputDir.mkdirs();
                }
                String outputFilePath = outputPath + File.separator + new File(imagePath).getName();
                Imaging.writeImage(resizedImage, new File(outputFilePath), ImageFormats.PNG);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {
        int originalWidth = imgSize.width;
        int originalHeight = imgSize.height;
        int boundWidth = boundary.width;
        int boundHeight = boundary.height;
        int newWidth = originalWidth;
        int newHeight = originalHeight;

        if (originalWidth > boundWidth) {
            newWidth = boundWidth;
            newHeight = (newWidth * originalHeight) / originalWidth;
        }

        if (newHeight > boundHeight) {
            newHeight = boundHeight;
            newWidth = (newHeight * originalWidth) / originalHeight;
        }

        return new Dimension(newWidth, newHeight);
    }

    public static void main(String[] args) {
        // Example usage:
        String[] imagePaths = {"path/to/image1.jpg", "path/to/image2.png"};
        String outputPath = "path/to/output";
        int maxWidth = 800;
        int maxHeight = 600;
        
        batchResizeImages(imagePaths, outputPath, maxWidth, maxHeight);
    }
}