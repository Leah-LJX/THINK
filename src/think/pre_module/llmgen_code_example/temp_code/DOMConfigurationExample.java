import org.w3c.dom.DOMConfiguration;

public class DOMConfigurationExample {
    public static void main(String[] args) {
        DOMConfiguration config = new DOMConfiguration();

        config.setParameter("cdata-sections", true);
        config.setParameter("comments", true);
        config.setParameter("namespaces", true);
        config.setParameter("whitespaces", true);

        boolean keepWhitespaces = (boolean) config.getParameter("whitespaces");
        System.out.println("Keep whitespaces: " + keepWhitespaces);
    }
}