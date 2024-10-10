import org.apache.pdfbox.text.TextPosition;

public class FontSizeExample {
    public static void main(String[] args) {
        TextPosition textPosition = new TextPosition();

        float fontSize = textPosition.getFontSize();
        System.out.println("Font size: " + fontSize);
    }
}