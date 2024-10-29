import java.io.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class CertificateLoader {

    public void loadCertificate(String certificatePath) throws FileNotFoundException {
        // load the recipient's certificate
        InputStream inStream = new FileInputStream(certificatePath);
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate certificate = (X509Certificate) cf.generateCertificate(inStream);
        inStream.close();
    }
}