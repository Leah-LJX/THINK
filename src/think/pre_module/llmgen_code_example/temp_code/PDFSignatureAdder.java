import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureOptions;

public class PDFSignatureAdder {
    public void addSignatureToDocument(PDDocument document, PDSignature signature, SignatureInterface signatureInterface, SignatureOptions options) {
        // Add a signature to the document
        document.addSignature(signature, signatureInterface, options);
    }
}