import org.apache.pdfbox.text.PDFParser;

public class PDFParserExample {
    public static void main(String[] args) {
        String filePath = "example.pdf";
        
        PDFParser parser = new PDFParser(filePath);
        parser.initialParse();
    }
}