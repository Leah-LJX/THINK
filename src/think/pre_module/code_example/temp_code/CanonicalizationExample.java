import org.w3c.dom.DOMConfiguration;

public class CanonicalizationExample {
    public static void main(String[] args) {
        DOMConfiguration domConfig = null; // Initialize with appropriate DOMConfiguration object
        boolean canonicalize = true;
        
        // Set the canonicalize parameter to true
        domConfig.setParameter("canonical-form", canonicalize);
        
        // Verify if the parameter is set to true
        System.out.println("Canonicalization is set to: " + domConfig.getParameter("canonical-form"));
    }
}