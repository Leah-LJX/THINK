import java.io.File;
import java.awt.Dimension;

public class ImageSizeExample {
    public static void main(String[] args) {
        File file = new File("example.jpg");
        ImageParser imageParser = new ImageParser();
        Dimension imageSize = imageParser.getImageSize(file, null);

        System.out.println("Image width: " + imageSize.width);
        System.out.println("Image height: " + imageSize.height);
    }
}