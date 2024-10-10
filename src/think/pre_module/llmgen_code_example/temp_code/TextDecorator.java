import org.apache.pdfbox.pdmodel.graphics.color.PDGamma;

public class TextDecorator {
    public PDGamma color;

    public TextDecorator(PDGamma color) {
        this.color = color;
    }

    public void setTextDecorationColor(PDGamma textDecorationColor) {
        this.color = textDecorationColor;
    }
}