import org.w3c.dom.Element;

public class ElementAttributeExample {
    public static void main(String[] args) {
        Element element = // initialize Element object
        String attributeName = "exampleAttribute";
        String attributeValue = element.getAttribute(attributeName);
        System.out.println("Attribute value: " + attributeValue);
    }
}