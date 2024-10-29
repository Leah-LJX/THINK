import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.ui.TextAnchor;

public class TextAnnotationExample {
    public static void main(String[] args) {
        XYTextAnnotation annotation = new XYTextAnnotation("Sample", 1.0, 2.0);
        TextAnchor textAnchor = annotation.getTextAnchor();
        System.out.println("Text Anchor: " + textAnchor);
    }
}