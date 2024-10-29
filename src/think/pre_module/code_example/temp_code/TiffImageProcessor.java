import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TiffImageProcessor {
    public static void main(String[] args) {
        File tiffFile = new File("example.tiff");
        TiffDirectory directory = TiffDirectory.SINGLE_PAGE;
        ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
        TiffImagingParameters params = new TiffImagingParameters();

        TiffImageParser parser = new TiffImageParser();
        try {
            BufferedImage bufferedImage = parser.getBufferedImage(directory, byteOrder, params);
            ImageIO.write(bufferedImage, "png", new File("output.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}