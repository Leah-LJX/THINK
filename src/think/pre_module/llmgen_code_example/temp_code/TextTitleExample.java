import org.jfree.chart.title.TextTitle;

public class TextTitleExample {
    public static void main(String[] args) {
        TextTitle title = new TextTitle();
        String tooltipText = "This is a tooltip";
        
        title.setToolTipText(tooltipText);
    }
}