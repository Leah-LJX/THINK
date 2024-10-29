import java.io.File;
import java.awt.Dimension;

public class ImageExample {

    public static void main(String[] args) {
        File file = new File("example.jpg");
        
        ImageParser parser = new ImageParser();
        Dimension imageSize = parser.getImageSize(file);
        
        System.out.println("Image size: " + imageSize.getWidth() + " x " + imageSize.getHeight());
    }
}