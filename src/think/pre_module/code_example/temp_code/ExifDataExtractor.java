import org.apache.commons.imaging.common.byteSources.ByteSource;
import org.apache.commons.imaging.formats.jpeg.JpegImageParser;

public class ExifDataExtractor {
    public byte[] extractExifData(ByteSource byteSource) {
        JpegImageParser jpegImageParser = new JpegImageParser();
        return jpegImageParser.getExifRawData(byteSource);
    }
}