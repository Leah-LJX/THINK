import java.io.File;
import java.awt.image.BufferedImage;

public class ImageProcessingExample {
    public static void main(String[] args) {
        File imageFile = new File("example.jpg");
        BufferedImage image = Imaging.getBufferedImage(imageFile);
        
        // Further image processing code here
    }
}