import org.w3c.dom.Node;

public class DOMLocator {
    public Node relatedNode;

    public Node getRelatedNode() {
        return relatedNode;
    }

    public void setRelatedNode(Node relatedNode) {
        this.relatedNode = relatedNode;
    }

    public static void main(String[] args) {
        DOMLocator locator = new DOMLocator();
        Node relatedNode = locator.getRelatedNode();
        if (relatedNode != null) {
            // handle related node
        } else {
            // no related node available
        }
    }
}