import org.w3c.dom.svg.SVGLengthList;

public class SVGLengthListExample {
    public static void main(String[] args) {
        SVGLengthList list = new SVGLengthList();
        int index = 0;
        SVGLength item = list.getItem(index);
        System.out.println("SVGLength at index " + index + ": " + item);
    }
}