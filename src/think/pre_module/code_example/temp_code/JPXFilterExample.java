import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import com.github.jaiimageio.jpeg2000.impl.J2KImageReaderSpi;

public class JPXFilterExample {
  
  public static void main(String[] args) {
    try {
      ImageInputStream input = ImageIO.createImageInputStream("inputFile.jpx");
      J2KImageReaderSpi jpxProvider = new J2KImageReaderSpi();
      jpxProvider.onRegistration();
      
      if (jpxProvider.canDecodeInput(input)) {
        jpxProvider.getOriginatingProviderName();
        // Perform other operations on JPXFilter here
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}