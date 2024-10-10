import com.example.PDEmbeddedFile;

public class EmbedFileUtil {
    public static void main(String[] args) {
        PDEmbeddedFile embeddedFile = new PDEmbeddedFile();
        String subtype = embeddedFile.getSubtype();
        System.out.println("Subtype of the embedded file: " + subtype);
    }
}