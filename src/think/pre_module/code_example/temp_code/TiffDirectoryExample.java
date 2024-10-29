import com.sun.media.imageio.plugins.tiff.TiffDirectory;

public class TiffDirectoryExample {
    public static void main (String[] args) {
        File tiffFile = new File("example.tiff");

        TiffDirectory tiffDir = new TiffDirectory(tiffFile);

        // Accessing IFD elements
        int[] tags = tiffDir.getTagList();

        // More API usage here
    }
}