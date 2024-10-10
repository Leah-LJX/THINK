import org.w3c.dom.Document;

public class XmlVersionGetter {
    public Document xmlDocument;

    public XmlVersionGetter(Document xmlDocument) {
        this.xmlDocument = xmlDocument;
    }

    public String getXmlVersion() {
        return xmlDocument.getXmlVersion();
    }
}