import org.apache.commons.imaging.ImageParser;
import org.apache.commons.imaging.common.ByteSource;
import org.apache.commons.imaging.formats.jpeg.JpegImagingParameters;
import org.apache.commons.imaging.formats.jpeg.JpegImageParser;

public class ImageSizeGetter {
    public Dimension getImageSize(ByteSource byteSource, JpegImagingParameters params) {
        ImageParser imageParser = new JpegImageParser();
        return imageParser.getImageSize(byteSource, params);
    }
}