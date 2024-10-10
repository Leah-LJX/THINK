import org.apache.commons.imaging.common.ImageBuilder;
import org.apache.commons.imaging.common.ImageFilter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {

    public static void applyConvolutionFilter(String imagePath, String outputPath, double[][] filterMatrix) {
        try {
            BufferedImage image = ImageBuilder.readImage(new File(imagePath));
            ImageFilter.applyConvolutionFilter(image, filterMatrix);
            ImageBuilder.writeImage(image, new File(outputPath), ImageBuilder.IMAGE_TYPE_JPEG, 100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}