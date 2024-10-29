import org.apache.pdfbox.pdmodel.common.PDLayoutAttributeObject;

public class PDLayoutAttributeObjectExample {
    public static void main(String[] args) {
        PDLayoutAttributeObject layoutAttribute = new PDLayoutAttributeObject();
        Object tBorderStyle = layoutAttribute.getTBorderStyle();
    }
}