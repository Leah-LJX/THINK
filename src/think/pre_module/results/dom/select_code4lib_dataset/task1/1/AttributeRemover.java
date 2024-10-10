import org.w3c.dom.*;

public class AttributeRemover {
    public Node removeNamedItemNS(String namespaceURI, String name, Element element) {
        return element.removeAttributeNode(element.getAttributeNodeNS(namespaceURI, name));
    }
}