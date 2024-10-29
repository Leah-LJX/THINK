import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputDirectory;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputField;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageParser;

import java.io.*;

public class ExifRewriterExample {

    public static void main(String[] args) {
        try {
            // Initialize input and output streams
            InputStream inputStream = new FileInputStream("input.jpg");
            OutputStream outputStream = new FileOutputStream("output.jpg");

            // Create a TiffOutputSet with new EXIF metadata
            TiffOutputSet outputSet = new TiffOutputSet();
            // Add or update EXIF metadata fields here

            // Use ExifRewriter to update EXIF metadata
            ExifRewriter rewriter = new ExifRewriter();
            rewriter.updateExifMetadataLossless(inputStream, outputStream, outputSet);

            // Close streams
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}