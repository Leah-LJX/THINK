import org.jfree.chart.plot.CategoryPlot;
import org.jfree.ui.SortOrder;

public class CategoryPlotExample {
    public static void main(String[] args) {
        CategoryPlot plot = new CategoryPlot();
        SortOrder order = SortOrder.DESCENDING;
        plot.setColumnRenderingOrder(order);
    }
}