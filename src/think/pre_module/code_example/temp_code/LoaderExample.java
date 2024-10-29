import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.InputStream;

public class LoaderExample {

    public static void main(String[] args) {
        byte[] pdfBytes = new byte[] { /* PDF byte array here */ };
        String password = "examplePassword";
        InputStream keyStore = /* input stream for keystore */;
        String alias = "exampleAlias";
        
        PDDocument document = Loader.loadPDF(pdfBytes, password, keyStore, alias);
        
        // Further operations on the loaded PDF document
    }
}