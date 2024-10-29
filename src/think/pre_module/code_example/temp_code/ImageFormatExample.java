import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.io.ByteSource;

public class ImageFormatExample {

    public static void main(String[] args) {
        ByteSource byteSource = // initialize with the byte data from the file
        ImageFormat format = Imaging.guessFormat(byteSource);
        System.out.println("The image format is: " + format);
    }
}