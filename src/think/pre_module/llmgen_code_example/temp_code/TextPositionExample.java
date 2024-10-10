import your.package.TextPosition;

public class TextPositionExample {
    public static void main(String[] args) {
        TextPosition textPosition = new TextPosition();
        float fontSize = textPosition.getFontSizeInPt();
        System.out.println("Font size: " + fontSize + "pt");
    }
}