import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDVisibleSigBuilder;

public class PDVisibleSigBuilderExample {

    public static void main(String[] args) {
        PDPage page = new PDPage();
        
        PDVisibleSigBuilder visibleSigBuilder = new PDVisibleSigBuilder();
        visibleSigBuilder.createTemplate(page);
        
        // Other code
    }
}