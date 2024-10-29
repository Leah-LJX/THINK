import org.jfree.chart.labels.CategoryToolTipGenerator;

public class ExampleUsage {
    public static void main(String[] args) {
        AbstractCategoryItemRenderer renderer = new AbstractCategoryItemRenderer();

        // Set the tool tip generator for a series
        int series = 1;
        CategoryToolTipGenerator generator = new CustomCategoryToolTipGenerator();
        renderer.setSeriesToolTipGenerator(series, generator);
    }
}