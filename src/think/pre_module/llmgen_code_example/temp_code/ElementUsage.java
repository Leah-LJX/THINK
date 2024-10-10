import org.w3c.dom.Element;

public class ElementUsage {
    public static void main(String[] args) {
        Element element = new Element();
        String attributeName = "exampleAttribute";
        String attributeValue = "exampleValue";
        
        element.setAttribute(attributeName, attributeValue);
    }
}