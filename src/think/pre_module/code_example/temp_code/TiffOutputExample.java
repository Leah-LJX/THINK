import com.sun.media.imageio.plugins.tiff.TIFFDirectory;
import com.sun.media.imageio.plugins.tiff.TiffOutputSet;

public class TiffOutputExample {
    public static void main(String[] args) {
        TiffOutputSet tiffOutputSet = new TiffOutputSet();
        
        TIFFDirectory rootDirectory = tiffOutputSet.getRootDirectory();
        
        // API usage example
        System.out.println("Root directory: " + rootDirectory.toString());
    }
}