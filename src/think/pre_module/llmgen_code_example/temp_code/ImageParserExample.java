import java.io.File;

public class ImageParserExample {
    public static void main(String[] args) {
        File imageFile = new File("example.jpg");
        
        ImageParser parser = new ImageParser();
        FormatCompliance compliance = parser.getFormatCompliance(imageFile);
        
        System.out.println("Format Compliance: " + compliance);
    }
}