import java.io.File;

public class ImageProcessing {
    public static void main(String[] args) {
        File imageFile = new File("example.jpg");

        ImageParser parser = new ImageParser();
        Dimension imageSize = parser.getImageSize(imageFile);

        System.out.println("Image size: " + imageSize);
    }
}