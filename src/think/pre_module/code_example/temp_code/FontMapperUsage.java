import org.apache.fontbox.FontMapping;
import org.apache.fontbox.FontBoxFont;
import org.apache.fontbox.type1.PDFontDescriptor;
import org.apache.fontbox.pdmodel.PDFont;

public class FontMapperUsage {

    public static void main(String[] args) {
        String baseFont = "Arial";
        PDFontDescriptor fontDescriptor = new PDFontDescriptor();
        
        FontMapper fontMapper = new FontMapper();
        FontMapping<FontBoxFont> mappedFont = fontMapper.getFontBoxFont(baseFont, fontDescriptor);
        
        if (mappedFont != null) {
            FontBoxFont font = mappedFont.getFont();
            System.out.println("Font found: " + font.getName());
        } else {
            System.out.println("Font not found or suitable substitute");
        }
    }
}