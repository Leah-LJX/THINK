import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;
import org.apache.commons.imaging.formats.jpeg.exif.ExifRewriter;

import java.io.*;

public class ExifMetadataUpdater {
    public static void main(String[] args) {
        try {
            File inputFile = new File("input.jpg");
            File outputFile = new File("output.jpg");

            InputStream inputStream = new FileInputStream(inputFile);
            OutputStream outputStream = new FileOutputStream(outputFile);

            TiffOutputSet outputSet = new TiffOutputSet();

            ExifRewriter rewriter = new ExifRewriter();
            rewriter.updateExifMetadataLossless(inputStream, outputStream, outputSet);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}