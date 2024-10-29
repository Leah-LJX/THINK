import com.example.text.TextPosition;

public class TextExample {
    public static void main(String[] args) {
        TextPosition textPosition = new TextPosition();
        float fontSize = textPosition.getFontSize();
        System.out.println("Font size: " + fontSize);
    }
}