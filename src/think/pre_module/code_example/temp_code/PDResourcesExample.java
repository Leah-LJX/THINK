import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.cos.COSName;

public class PDResourcesExample {
    public static void main(String[] args) {
        // Assume PDResources object is initialized and assigned to variable 'pdResources'
        
        Iterable<COSName> extGStateNames = pdResources.getExtGStateNames();
        
        for (COSName name : extGStateNames) {
            System.out.println(name.getName());
        }
    }
}