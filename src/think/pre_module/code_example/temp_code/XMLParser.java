import org.w3c.dom.Document;

public class XmlParser {
    public Document document;

    public XmlParser(Document document) {
        this.document = document;
    }

    public String retrieveXmlVersion() {
        return document.getXmlVersion();
    }
}