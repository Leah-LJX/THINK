import java.io.File;

public class FDFDocumentUsage {
    public static void main(String[] args) {
        // Initialize the file name
        File file = new File("example.fdf");

        // Create a new FDFDocument instance
        FDFDocument document = new FDFDocument();

        // Save the document to the filesystem
        document.save(file);
    }
}