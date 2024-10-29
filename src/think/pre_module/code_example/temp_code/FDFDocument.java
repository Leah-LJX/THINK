import java.io.File;

public class FDFDocument {
    String content;

    public FDFDocument(String content) {
        this.content = content;
    }

    public void save(String fileName) {
        // Implementation to save the document to the filesystem
    }
}

public class Main {
    public static void main(String[] args) {
        FDFDocument document = new FDFDocument("Sample content");
        String fileName = "document.fdf";
        document.save(fileName);
    }
}