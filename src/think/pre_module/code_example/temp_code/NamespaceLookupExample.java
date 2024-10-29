import org.w3c.dom.Node;

public class NamespaceLookupExample {
    public static void main(String[] args) {
        Node node = // initialize node here
        String prefix = "example";
        String namespaceURI = node.lookupNamespaceURI(prefix);
        System.out.println("Namespace URI for prefix " + prefix + " is: " + namespaceURI);
    }
}