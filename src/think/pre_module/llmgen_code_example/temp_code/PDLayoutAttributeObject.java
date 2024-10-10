import org.apache.pdfbox.pdmodel.graphics.color.PDGamma;

public class PDLayoutAttributeObject {
    public PDGamma backgroundColor;

    public void setBackgroundColor(PDGamma backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}

// API usage example
PDLayoutAttributeObject layout = new PDLayoutAttributeObject();
PDGamma color = new PDGamma(0.5f, 0.8f, 0.2f);
layout.setBackgroundColor(color);