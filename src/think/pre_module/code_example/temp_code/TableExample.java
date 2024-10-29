import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDLayoutAttributeObject;

public class TableExample {
    public static void main(String[] args) {
        PDLayoutAttributeObject cell = new PDLayoutAttributeObject();
        Object borderStyle = cell.getTBorderStyle();
        System.out.println("Border Style: " + borderStyle);
    }
}