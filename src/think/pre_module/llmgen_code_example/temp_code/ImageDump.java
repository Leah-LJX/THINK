import java.awt.image.BufferedImage;

public class ImageDump {
    public static void main(String[] args) {
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        ImageDump imageDump = new ImageDump();
        imageDump.dump(image);
    }

    public void dump(BufferedImage src) {
        // implementation
    }
}