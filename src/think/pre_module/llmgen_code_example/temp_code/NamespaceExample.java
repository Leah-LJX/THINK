import org.w3c.dom.Node;

public class NamespaceExample {
    public static void main(String[] args) {
        Node node = // initialize Node object
        String prefix = // initialize prefix
        String namespaceURI = node.lookupNamespaceURI(prefix);
        System.out.println("Namespace URI for prefix " + prefix + ": " + namespaceURI);
    }
}