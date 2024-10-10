import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

public class DOMImplementationExample {
    public static void main(String[] args) {
        DOMImplementation domImpl = Document.getImplementation();
        System.out.println("DOM Implementation: " + domImpl);
    }
}