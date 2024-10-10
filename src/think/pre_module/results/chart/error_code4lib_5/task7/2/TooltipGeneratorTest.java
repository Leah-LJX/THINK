import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;

public class TooltipGeneratorTest {

    public void testSetSeriesToolTipGenerator() {
        // Create a CategoryItemRenderer or XYItemRenderer instance
        CategoryItemRenderer categoryRenderer = new CategoryItemRenderer();
        XYItemRenderer xyRenderer = new XYItemRenderer();

        // Set a custom tool tip generator for a series
        categoryRenderer.setSeriesToolTipGenerator(0, new CustomCategoryToolTipGenerator());
        xyRenderer.setSeriesToolTipGenerator(0, new CustomXYToolTipGenerator());

        // Check if the default generator is overridden
        // This can be done by inspecting the tooltip text for a specific data point
        // and verifying that it matches the custom generator's output
        // You can use a test framework like JUnit to perform this validation
    }
}