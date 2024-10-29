import org.apache.pdfbox.text.TextPosition;

public class TextProcessor {
    public static void main(String[] args) {
        TextPosition textPosition = new TextPosition();
        float fontSize = textPosition.getFontSizeInPt();
        System.out.println("Font size in pt: " + fontSize);
    }
}