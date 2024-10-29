import com.drew.imaging.ImageMetadataReader;
import com.drew.lang.ByteSource;
import com.drew.metadata.exif.TiffImageMetadata;
import com.drew.metadata.jpeg.JpegImageParser;
import com.drew.imaging.TiffImagingParameters;

public class UsageExample {
    public static void main(String[] args) {
        ByteSource byteSource = null; // initialize with actual byte source
        TiffImagingParameters params = null; // initialize with actual imaging parameters

        TiffImageMetadata exifMetadata = ImageMetadataReader.getExifMetadata(byteSource, params, new JpegImageParser());
    }
}