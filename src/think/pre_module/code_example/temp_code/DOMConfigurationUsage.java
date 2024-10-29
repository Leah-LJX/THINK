import org.w3c.dom.DOMConfiguration;

public class DOMConfigurationUsage {

    public static void main(String[] args) {
        DOMConfiguration domConfig = new DOMConfiguration();

        // Keep Comment nodes in the document
        domConfig.setParameter("comments", true);

        // Discard Comment nodes in the document
        domConfig.setParameter("comments", false);
    }
}