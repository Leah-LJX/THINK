import org.apache.pdfbox.pdmodel.common.PDViewerPreferences;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;

public class ViewerPreferencesExample {
    public static void main(String[] args) {
        PDDocumentCatalog catalog = new PDDocumentCatalog();
        PDViewerPreferences viewerPreferences = new PDViewerPreferences();
        
        // set viewer preferences
        catalog.setViewerPreferences(viewerPreferences);
    }
}