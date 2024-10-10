import org.apache.commons.imaging.common.ImageBuilder;
import org.apache.commons.imaging.common.ImageFilter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {

    public static void applyConvolutionFilter(String imagePath, String outputPath, double[][] filterMatrix) {
        try {
            BufferedImage image = ImageBuilder.getImage(new File(imagePath));
            ImageFilter.applyConvolutionFilter(image, filterMatrix);
            ImageBuilder.saveImage(image, new File(outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}