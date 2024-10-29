import com.example.ByteSource;
import com.example.ImageMetadata;
import com.example.ImageParser;

public class ImageMetadataGetter {
    public static void main(String[] args) {
        ByteSource byteSource = new ByteSource();
        // populate the byteSource with actual data

        ImageParser imageParser = new ImageParser();
        ImageMetadata metadata = imageParser.getMetadata(byteSource);

        // make use of the metadata
    }
}