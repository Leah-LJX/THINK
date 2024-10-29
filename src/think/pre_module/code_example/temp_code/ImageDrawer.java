import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.PDAppearanceContentStream;

public class ImageDrawer {
    public void drawImageAtCoordinates(PDImageXObject image, float x, float y, float width, float height) {
        PDAppearanceContentStream contentStream = new PDAppearanceContentStream();
        contentStream.drawImage(image, x, y, width, height);
        // rest of the code
    }
}