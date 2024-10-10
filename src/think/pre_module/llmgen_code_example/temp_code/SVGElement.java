import org.w3c.dom.css.CSSStyleDeclaration;

public class SVGElement implements SVGStylable {
    public CSSStyleDeclaration style;

    public void setStyle(CSSStyleDeclaration style) {
        this.style = style;
    }

    public CSSStyleDeclaration getStyle() {
        return style;
    }
}