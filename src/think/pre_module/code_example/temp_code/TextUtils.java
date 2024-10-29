import org.w3c.dom.Node;

public class TextUtils {
    public static void main(String[] args) {
        String xmlContent = "<root><p>This is some sample text.</p></root>";
        String newText = "This is the new text.";

        Node node = getNodeFromXML(xmlContent);
        if (node != null) {
            if (node.getNodeType() == Node.TEXT_NODE) {
                Text textNode = (Text) node;
                textNode.replaceWholeText(newText);
                System.out.println("Text replaced successfully.");
            } else {
                System.out.println("The provided node is not a text node.");
            }
        } else {
            System.out.println("Invalid XML content.");
        }
    }

    public static Node getNodeFromXML(String xmlContent) {
        // Implementation code to parse XML and get the desired node
        return null;
    }
}