import org.apache.fontbox.ttf.TrueTypeFont;
import org.apache.fontbox.pdmodel.font.PDFontDescriptor;
import org.apache.fontbox.cff.TrueTypeFont;

public class FontMappingExample {

    public static void main(String[] args) {
        String baseFont = "Arial";
        PDFontDescriptor fontDescriptor = new PDFontDescriptor();

        FontMapping<TrueTypeFont> fontMapping = new FontMapper().getTrueTypeFont(baseFont, fontDescriptor);

        if (fontMapping != null) {
            TrueTypeFont trueTypeFont = fontMapping.getFont();
            System.out.println("Found TrueType font: " + trueTypeFont.getName());
        } else {
            System.out.println("TrueType font not found.");
        }
    }
}