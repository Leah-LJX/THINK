import org.apache.pdfbox.contentstream.PDFStreamEngine;

public class GraphicsStateExample {
    public static void main(String[] args) {
        PDFStreamEngine pdfStreamEngine = new PDFStreamEngine();
        
        // ... some code
        
        pdfStreamEngine.restoreGraphicsState();
        
        // ... some more code
    }
}