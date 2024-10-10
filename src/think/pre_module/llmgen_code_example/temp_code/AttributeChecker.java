import org.w3c.dom.Element;

public class AttributeChecker {
    public static void main(String[] args) {
        Element element = // initialize the Element object
        String attributeName = "exampleAttribute";
        
        boolean hasAttribute = element.hasAttribute(attributeName);
        System.out.println("Element has attribute: " + hasAttribute);
    }
}