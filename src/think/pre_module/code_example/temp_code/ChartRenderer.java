import org.jfree.chart.labels.CategoryToolTipGenerator;

public class ChartRenderer {

    public AbstractCategoryItemRenderer renderer;

    public ChartRenderer(AbstractCategoryItemRenderer renderer) {
        this.renderer = renderer;
    }

    public void setSeriesToolTipGenerator(int series, CategoryToolTipGenerator generator) {
        renderer.setSeriesToolTipGenerator(series, generator);
    }
}