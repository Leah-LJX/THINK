import org.w3c.dom.svg.SVGTextContentElement;
import org.w3c.dom.svg.SVGRect;

public class SVGExample {
    public static void main(String[] args) {
        SVGTextContentElement textContentElement = getTextContentElement(); // This method should return a valid SVGTextContentElement
        int charnum = 5;
        
        SVGRect charExtent = textContentElement.getExtentOfChar(charnum);
        System.out.println("Extent of character " + charnum + ": (" + charExtent.getX() + ", " + charExtent.getY() + ", " + charExtent.getWidth() + ", " + charExtent.getHeight() + ")");
    }
    
    public static SVGTextContentElement getTextContentElement() {
        // Implement method to return a valid SVGTextContentElement
        return null;
    }
}