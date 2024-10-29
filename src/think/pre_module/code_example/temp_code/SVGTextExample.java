import org.w3c.dom.svg.SVGTextContentElement;

public class SVGTextExample {
    public static void main(String[] args) {
        SVGTextContentElement textElement = null;
        int numberOfChars = textElement.getNumberOfChars();
        System.out.println("Number of Characters: " + numberOfChars);
    }
}