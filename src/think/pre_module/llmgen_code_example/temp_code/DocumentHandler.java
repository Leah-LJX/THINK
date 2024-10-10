import com.example.FDFDocument;

public class DocumentHandler {
    public static void main(String[] args) {
        FDFDocument document = new FDFDocument();
        String fileName = "example.pdf";
        document.save(fileName);
    }
}