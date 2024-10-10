import org.w3c.dom.Text;

public class TextManipulation {
    public static void main(String[] args) {
        String initialContent = "Initial content";
        Text textNode = new Text();

        String newContent = "New content";
        textNode.replaceWholeText(newContent);
    }
}