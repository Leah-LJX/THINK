import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDPatternContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDMatrix;

public class ImageDrawingExample {
    public static void main(String[] args) {
        PDImageXObject image = // initialize with image object
        Matrix matrix = // initialize with transformation matrix
        PDPatternContentStream patternContentStream = new PDPatternContentStream();
        patternContentStream.drawImage(image, matrix);
    }
}