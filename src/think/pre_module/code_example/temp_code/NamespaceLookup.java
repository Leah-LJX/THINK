import org.w3c.dom.Node;

public class NamespaceLookup {
    public static void main(String[] args) {
        Node node = // initialize Node object
        String prefix = "example";
        String namespaceURI = node.lookupNamespaceURI(prefix);
        System.out.println("Namespace URI for prefix " + prefix + " is " + namespaceURI);
    }
}