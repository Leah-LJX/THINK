import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.PDAppearanceContentStream;

public class PDColorExample {
    public static void main(String[] args) {
        PDColor color = new PDColor();
        PDAppearanceContentStream appearanceContentStream = new PDAppearanceContentStream();

        // Set non-stroking color
        appearanceContentStream.setNonStrokingColor(color);
    }
}