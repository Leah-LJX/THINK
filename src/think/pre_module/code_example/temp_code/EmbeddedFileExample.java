import com.example.PDEmbeddedFile;

public class EmbeddedFileExample {

    public static void main(String[] args) {
        PDEmbeddedFile embeddedFile = new PDEmbeddedFile();
        String subtype = embeddedFile.getSubtype();
        System.out.println("Embedded file subtype: " + subtype);
    }
}