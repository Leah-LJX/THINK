import org.apache.pdfbox.pdmodel.font.PDVectorFont;

public class PDVectorFontUsageExample {
    public static void main(String[] args) {
        PDVectorFont font = new PDVectorFont();

        int code = 65; // character code
        boolean hasGlyph = font.hasGlyph(code);
        System.out.println("Font contains glyph for character code 65: " + hasGlyph);
    }
}