import com.example.FDFDocument;

public class FDFDocumentExample {
    public static void main(String[] args) {
        String fileName = "example.fdf";
        FDFDocument document = new FDFDocument();
        document.save(fileName);
    }
}