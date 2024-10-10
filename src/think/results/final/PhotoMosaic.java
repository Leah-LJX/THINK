import org.apache.commons.imaging.ImageReadException;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import org.apache.commons.imaging.Imaging;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class PhotoMosaic {
    public static void createPhotoMosaic(String targetImagePath, String[] tileImages, String outputPath) throws ImageReadException , ImageReadException {
        try {
            // Read target image
            BufferedImage targetImage = Imaging.getBufferedImage(new File(targetImagePath));

            // Define tile size
            int tileWidth = 50; // You can adjust the size
            int tileHeight = 50; // You can adjust the size

            // Read and resize tile images
            List<BufferedImage> tiles = new ArrayList<>();
            for (String tileImagePath : tileImages) {
                BufferedImage tileImage = Imaging.getBufferedImage(new File(tileImagePath));
                BufferedImage resizedTile = resizeImage(tileImage, tileWidth, tileHeight);
                tiles.add(resizedTile);
            }

            // Calculate average color for each tile image
            List<Color> tileColors = new ArrayList<>();
            for (BufferedImage tile : tiles) {
                tileColors.add(calculateAverageColor(tile));
            }

            // Create a mosaic
            int mosaicWidth = targetImage.getWidth();
            int mosaicHeight = targetImage.getHeight();
            BufferedImage mosaic = new BufferedImage(mosaicWidth, mosaicHeight, BufferedImage.TYPE_INT_RGB);
            Graphics g = mosaic.getGraphics();

            for (int y = 0; y < mosaicHeight; y += tileHeight) {
                for (int x = 0; x < mosaicWidth; x += tileWidth) {
                    // Get the subimage from the target image
                    BufferedImage subImage = targetImage.getSubimage(x, y, Math.min(tileWidth, mosaicWidth - x), Math.min(tileHeight, mosaicHeight - y));
                    Color averageColor = calculateAverageColor(subImage);

                    // Find the best matching tile
                    int bestMatchIndex = 0;
                    double bestMatchDistance = Double.MAX_VALUE;
                    for (int i = 0; i < tileColors.size(); i++) {
                        double distance = colorDistance(averageColor, tileColors.get(i));
                        if (distance < bestMatchDistance) {
                            bestMatchDistance = distance;
                            bestMatchIndex = i;
                        }
                    }

                    // Draw the best matching tile
                    g.drawImage(tiles.get(bestMatchIndex), x, y, null);
                }
            }

            g.dispose();

            // Save the mosaic
            ImageIO.write(mosaic, "JPEG", new File(outputPath));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        Image tmp = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    public static Color calculateAverageColor(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        long sumRed = 0, sumGreen = 0, sumBlue = 0;
        int pixelCount = width * height;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                sumRed += color.getRed();
                sumGreen += color.getGreen();
                sumBlue += color.getBlue();
            }
        }

        return new Color((int) (sumRed / pixelCount), (int) (sumGreen / pixelCount), (int) (sumBlue / pixelCount));
    }

    public static double colorDistance(Color c1, Color c2) {
        int redDiff = c1.getRed() - c2.getRed();
        int greenDiff = c1.getGreen() - c2.getGreen();
        int blueDiff = c1.getBlue() - c2.getBlue();
        return Math.sqrt(redDiff * redDiff + greenDiff * greenDiff + blueDiff * blueDiff);
    }

    public static void main(String[] args) {
        String targetImagePath = "path/to/target/image.jpg";
        String[] tileImages = {"path/to/tile1.jpg", "path/to/tile2.jpg", "path/to/tile3.jpg"};
        String outputPath = "path/to/output/mosaic.jpg";

        createPhotoMosaic(targetImagePath, tileImages, outputPath);
    }
}