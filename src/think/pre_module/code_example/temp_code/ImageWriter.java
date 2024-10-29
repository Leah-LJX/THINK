import java.awt.image.BufferedImage;
import java.io.File;

public class ImageWriter {
    public static void main(String[] args) {
        BufferedImage image = // initialize the BufferedImage
        File outputFile = new File("output.jpg");
        ImageFormats format = ImageFormats.JPG;

        Imaging.writeImage(image, outputFile, format);
    }
}