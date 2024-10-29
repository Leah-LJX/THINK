import org.w3c.dom.svg.SVGDocument;

public class SVGParser {
    public static void main(String[] args) {
        SVGDocument document = new SVGDocument();
        String url = document.getURL();
        System.out.println(url);
    }
}