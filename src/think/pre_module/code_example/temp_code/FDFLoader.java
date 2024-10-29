import com.example.Loader;

public class FDFLoader {
    public static void main(String[] args) {
        String filename = "example.fdf";
        FDFDocument document = Loader.loadFDF(filename);
        // rest of the code to work with the loaded FDF document
    }
}