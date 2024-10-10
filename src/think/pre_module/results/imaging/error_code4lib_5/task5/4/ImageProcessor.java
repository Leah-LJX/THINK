import org.apache.commons.imaging.common.ImageBuilder;
import org.apache.commons.imaging.common.ImageFilter;

public class ImageProcessor {
    public static void applyConvolutionFilter(String imagePath, String outputPath, double[][] filterMatrix) {
        try {
            ImageBuilder image = ImageBuilder.read(imagePath);
            ImageFilter filter = new ImageFilter(filterMatrix);
            ImageBuilder filteredImage = filter.applyFilter(image);
            filteredImage.write(outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}