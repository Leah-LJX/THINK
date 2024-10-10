import com.google.common.io.ByteSource;

public class TiffImageParser {

    public static void main(String[] args) {
        ByteSource byteSource = // initialize with actual byte source
        TiffImagingParameters params = // initialize with actual TiffImagingParameters
        TiffImageParser parser = new TiffImageParser();
        
        List<byte[]> rawImages = parser.collectRawImageData(byteSource, params);        
        // Use rawImages for further processing
    }
}