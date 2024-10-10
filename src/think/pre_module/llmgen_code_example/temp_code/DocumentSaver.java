import com.example.FDFDocument;

public class DocumentSaver {
    public static void main(String[] args) {
        String fileName = "example.pdf";
        FDFDocument document = new FDFDocument();
        document.save(fileName);
    }
}