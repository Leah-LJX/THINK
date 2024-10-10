import org.apache.pdfbox.pdmodel.graphics.state.PDGraphicsState;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;

public class PDGraphicsStateExample {
    public static void main(String[] args) {
        PDGraphicsState graphicsState = new PDGraphicsState();
        PDColor color = new PDColor(255, 0, 0);
        
        graphicsState.setNonStrokingColor(color);
    }
}