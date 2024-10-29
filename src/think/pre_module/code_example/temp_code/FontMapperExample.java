import org.apache.fontbox.ttf.TrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDFontDescriptor;
import org.apache.pdfbox.pdmodel.font.FontMapping;

public class FontMapperExample {
    public static void main(String[] args) {
        FontMapper fontMapper = new FontMapper();

        String baseFont = "TimesNewRoman";
        PDFontDescriptor fontDescriptor = new PDFontDescriptor();

        TrueTypeFont trueTypeFont = fontMapper.getTrueTypeFont(baseFont, fontDescriptor);

        if (trueTypeFont != null) {
            System.out.println("TrueType font found: " + trueTypeFont);
        } else {
            System.out.println("TrueType font not found");
        }
    }
}