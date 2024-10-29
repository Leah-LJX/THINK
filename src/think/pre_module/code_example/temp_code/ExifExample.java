import org.apache.sanselan.formats.tiff.TiffImageMetadata;
import org.apache.sanselan.formats.jpeg.JpegImageMetadata;

public class ExifExample {
    public static void main(String[] args) {
        JpegImageMetadata jpegImageMetadata = new JpegImageMetadata(null);
        TiffImageMetadata exif = jpegImageMetadata.getExif();
        
        // Use exif data
    }
}