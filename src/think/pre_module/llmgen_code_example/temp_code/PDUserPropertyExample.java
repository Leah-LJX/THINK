import org.apache.pdfbox.pdmodel.PDUserProperty;

public class PDUserPropertyExample {
    public static void main(String[] args) {
        // Instantiate a PDUserProperty object
        PDUserProperty userProperty = new PDUserProperty();

        // Check if the property is hidden
        boolean isHidden = userProperty.isHidden();

        // Print the result
        System.out.println("Is hidden: " + isHidden);
    }
}