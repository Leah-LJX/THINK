import org.w3c.dom.Element;

public class ElementExample {
    
    public static void main(String[] args) {
        Element element = // initialize with an actual Element object
        String attributeName = "exampleAttribute";
        
        boolean hasAttribute = element.hasAttribute(attributeName);
        
        System.out.println("Element has attribute " + attributeName + ": " + hasAttribute);
    }
}