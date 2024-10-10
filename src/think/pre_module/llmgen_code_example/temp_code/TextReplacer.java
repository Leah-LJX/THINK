import org.w3c.dom.Text;

public class TextReplacer {
    public static void main(String[] args) {
        String initialContent = "This is the initial content";
        Text textNode = getTextNode(); // This method returns the text node to be replaced
        String newContent = "This is the new content";

        replaceWholeText(textNode, newContent);
    }

    public static void replaceWholeText(Text textNode, String newContent) {
        textNode.replaceWholeText(newContent);
    }

    public static Text getTextNode() {
        // Code to retrieve the text node
        return textNode;
    }
}