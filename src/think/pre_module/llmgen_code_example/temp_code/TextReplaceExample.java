import org.w3c.dom.Text;

public class TextReplaceExample {
    public static void main(String[] args) {
        String originalContent = "This is the original text.";
        Text textNode = new Text();
        
        String newContent = "This is the new text.";
        textNode.replaceWholeText(newContent);
        
        System.out.println("After replacement: " + textNode.getData());
    }
}