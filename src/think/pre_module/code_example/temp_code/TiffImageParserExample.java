import java.awt.image.BufferedImage;
import com.example.TiffDirectory;
import com.example.ByteOrder;
import com.example.TiffImagingParameters;

public class TiffImageParserExample {

    public static void main(String[] args) {
        TiffDirectory directory = new TiffDirectory();
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        TiffImagingParameters params = new TiffImagingParameters();

        TiffImageParser parser = new TiffImageParser();
        BufferedImage image = parser.getBufferedImage(directory, byteOrder, params);

        // Rest of the code to use the image
    }
}