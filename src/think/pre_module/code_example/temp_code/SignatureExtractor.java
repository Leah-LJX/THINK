import java.io.InputStream;

public class SignatureExtractor {
    public static void main(String[] args) {
        InputStream pdfFile = // initialize with the input stream of the PDF file
        PDSignature signature = new PDSignature();
        byte[] contents = signature.getContents(pdfFile);
        // further processing of the extracted signature contents
    }
}