import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifReader;
import com.drew.metadata.exif.GpsDirectory;
import com.drew.metadata.jpeg.JpegSegmentType;
import com.drew.metadata.tiff.TiffOutputSet;
import com.drew.metadata.Directory;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
import java.io.InputStream;
import java.io.OutputStream;

public class ExifRewriterUsageExample {

    public static void main(String[] args) {
        InputStream inputStream = // initialize input stream with the jpeg image
        OutputStream outputStream = // initialize output stream to write the result
        
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(inputStream);
            TiffOutputSet outputSet = metadata.getFirstDirectoryOfType(TiffOutputSet.class);
            
            // Modify the metadata
            if (outputSet != null) {
                // for example: outputSet.getOrCreateExifIFD0Directory().setDate(ExifIFD0Directory.TAG_DATETIME, new Date());
                // modify other metadata values as needed
            }
            
            ExifRewriter rewriter = new ExifRewriter();
            rewriter.updateExifMetadataLossy(inputStream, outputStream, outputSet);
            
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}