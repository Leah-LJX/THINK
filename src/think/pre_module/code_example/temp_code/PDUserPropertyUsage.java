import org.apache.pdfbox.pdmodel.PDUserProperty;

public class PDUserPropertyUsage {
    public static void main(String[] args) {
        // Initialize some PDUserProperty object
        PDUserProperty userProperty = new PDUserProperty();
        
        // Check if the property is hidden
        boolean hidden = userProperty.isHidden();
        System.out.println("Is hidden: " + hidden);
    }
}