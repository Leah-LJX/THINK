import org.apache.pdfbox.pdmodel.graphics.state.PDTextState;

public class TextStateExample {
    public static void main(String[] args) {
        PDTextState textState = new PDTextState();
        float characterSpacing = textState.getCharacterSpacing();
        System.out.println("Character spacing: " + characterSpacing);
    }
}