import org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDVisibleSigBuilder;

public class SignatureCreator {

    public void createSignature(PDSignatureField pdSignatureField, PDPage page, String signerName) {
        PDVisibleSigBuilder visibleSigBuilder = new PDVisibleSigBuilder();
        visibleSigBuilder.printSignerName(signerName);
        visibleSigBuilder.buildSignature();
        visibleSigBuilder.signField(pdSignatureField);
        visibleSigBuilder.buildSignatureField(page);
    }
}