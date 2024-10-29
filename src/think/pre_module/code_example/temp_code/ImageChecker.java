import java.io.File;
import some.other.package.Imaging;

public class ImageChecker {
    public static void main(String[] args) {
        File imgFile = new File("image.jpg");
        String format = Imaging.guessFormat(imgFile);
        System.out.println("The image format is: " + format);
    }
}