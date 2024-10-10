import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.COSDocument;

public class LinearizedInfoGetter {
    public static void main(String[] args) {
        // Assume pdfDocument is an existing COSDocument object
        COSDocument pdfDocument = null; // Initialize with actual COSDocument object
        COSDictionary linearizedDictionary = pdfDocument.getLinearizedDictionary();
        
        if(linearizedDictionary != null) {
            // Do something with the linearizedDictionary
            System.out.println("Linearization information found: " + linearizedDictionary);
        } else {
            System.out.println("Pdf is not linearized");
        }
    }
}