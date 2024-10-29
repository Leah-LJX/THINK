import org.jfree.ui.TextAnchor;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.ItemLabelAnchor;

public class ItemLabelPositionExample {

    public static void main(String[] args) {
        ItemLabelAnchor itemLabelAnchor = ItemLabelAnchor.CENTER;
        TextAnchor textAnchor = TextAnchor.CENTER;

        ItemLabelPosition itemLabelPosition = new ItemLabelPosition(itemLabelAnchor, textAnchor);

        // Other code using itemLabelPosition
    }
}