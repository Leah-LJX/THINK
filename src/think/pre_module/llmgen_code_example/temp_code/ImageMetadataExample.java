import some.package.Imaging;
import some.package.ImageMetadata;
import java.io.InputStream;

public class ImageMetadataExample {
    public static void main(String[] args) {
        InputStream inputStream = //initialize with input stream of image file
        String fileName = "example.jpg";
        
        ImageMetadata metadata = Imaging.getMetadata(inputStream, fileName);
        
        // Use metadata object as needed
    }
}