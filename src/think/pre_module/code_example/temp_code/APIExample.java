import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.pdmodel.DefaultResourceCache;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;

public class APIExample {

    public static void main(String[] args) {
        COSObject indirectObject = // initialize with actual value or set to null
        DefaultResourceCache resourceCache = new DefaultResourceCache();

        PDExtendedGraphicsState extGState = resourceCache.getExtGState(indirectObject);
        
        if (extGState != null) {
            // handle the extended graphics state resource
        }
    }
}