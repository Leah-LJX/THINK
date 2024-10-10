import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlAttributeExample {
    public static void main(String[] args) {
        try {
            // Initialize a DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse an XML file (replace "example.xml" with a valid XML file path)
            Document document = builder.parse("example.xml");

            // Get the root element
            Element rootElement = document.getDocumentElement();

            // Get attributes from the root element
            NamedNodeMap attributes = rootElement.getAttributes();

            // Iterate over the attributes and print them
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attribute = attributes.item(i);
                System.out.println(attribute.getNodeName() + ": " + attribute.getNodeValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}