import org.apache.pdfbox.pdmodel.graphics.shading.PDShading;
import org.apache.pdfbox.pdmodel.graphics.pattern.PDShadingPattern;

public class ShadingPatternExample {
    public PDShadingPattern pattern;

    public PDShading getShading() {
        return pattern.getShading();
    }
}