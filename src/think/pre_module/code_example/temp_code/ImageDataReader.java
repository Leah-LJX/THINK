import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

public class ImageDataReader {
    public ImageInputStream input;
    public int width;
    public int height;

    public ImageDataReader(ImageInputStream input, int width, int height) {
        this.input = input;
        this.width = width;
        this.height = height;
    }

    public void readImageData() {
        // Perform image data reading here
    }

    public static void main(String[] args) {
        // Example API usage
        ImageInputStream inputStream = null; // initialize with actual stream
        int imageWidth = 100; // actual image width
        int imageHeight = 100; // actual image height

        ImageDataReader reader = new ImageDataReader(inputStream, imageWidth, imageHeight);
        reader.readImageData();
    }
}