import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.fontbox.ttf.TrueTypeFont;

public class FontLoader {
    public static void main(String[] args) {
        PDDocument document = new PDDocument();
        TrueTypeFont trueTypeFont = null; // Initialize TrueTypeFont with the desired font

        PDType0Font loadedFont = PDType0Font.load(document, trueTypeFont, true);
        
        // Rest of the code using the loadedFont
    }
}