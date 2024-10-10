import org.apache.pdfbox.pdmodel.graphics.shading.PDShadingType2;
import org.apache.pdfbox.pdmodel.common.COSArray;

public class PDShadingType2Example {
    public static void main(String[] args) {
        COSArray newDomain = new COSArray();
        // initialize newDomain

        PDShadingType2 shading = new PDShadingType2();
        shading.setDomain(newDomain);
    }
}