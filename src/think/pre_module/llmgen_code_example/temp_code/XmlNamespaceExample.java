import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;
import java.io.StringReader;

public class XmlNamespaceExample {
    public static void main(String[] args) {
        try {
            String xmlContent = "<root xmlns:example=\"http://www.example.com\">"
                              + "<example:child>Content 1</example:child>"
                              + "<example:child>Content 2</example:child>"
                              + "<child>Content 3</child>"
                              + "</root>";
                              
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlContent)));
            
            String namespaceURI = "http://www.example.com";
            String localName = "child";
            NodeList nodeList = document.getElementsByTagNameNS(namespaceURI, localName);
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                System.out.println("Found element: " + element.getTextContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}