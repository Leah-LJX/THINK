import some.package.FDFDocument;

public class DocumentManager {
    public static void main(String[] args) {
        FDFDocument document = new FDFDocument();
        String fileName = "example.pdf";
        document.save(fileName);
    }
}