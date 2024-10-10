import java.io.File;

public class ImageInfo {
    public static void main(String[] args) {
        File imageFile = new File("example.jpg");
        ImageParser parser = new ImageParser();
        Dimension imageSize = parser.getImageSize(imageFile, null);
        System.out.println("Image size: " + imageSize);
    }
}

class ImageParser {
    public Dimension getImageSize(File file, T params) {
        // Code to get the image size
        return new Dimension(200, 150);
    }
}