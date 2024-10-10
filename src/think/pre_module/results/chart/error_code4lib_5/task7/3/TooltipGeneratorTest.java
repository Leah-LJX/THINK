import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;

public class TooltipGeneratorTest {

    public void testSetSeriesToolTipGenerator() {
        // Create a category item renderer
        CategoryItemRenderer categoryRenderer = new CategoryItemRenderer() {
            @Override
            public CategoryToolTipGenerator getToolTipGenerator(int row, int column) {
                return null; // Return null to check if the default generator is overridden
            }
        };

        // Set the tool tip generator for a series
        categoryRenderer.setSeriesToolTipGenerator(0, new CustomCategoryToolTipGenerator());

        // Check if the default generator is overridden
        if (categoryRenderer.getToolTipGenerator(0, 0) instanceof CustomCategoryToolTipGenerator) {
            System.out.println("Setting a tool tip generator for a series overrides the default generator");
        } else {
            System.out.println("Setting a tool tip generator for a series does not override the default generator");
        }

        // Create an XY item renderer
        XYItemRenderer xyRenderer = new XYItemRenderer() {
            @Override
            public XYToolTipGenerator getToolTipGenerator(int series, int item) {
                return null; // Return null to check if the default generator is overridden
            }
        };

        // Set the tool tip generator for a series
        xyRenderer.setSeriesToolTipGenerator(0, new CustomXYToolTipGenerator());

        // Check if the default generator is overridden
        if (xyRenderer.getToolTipGenerator(0, 0) instanceof CustomXYToolTipGenerator) {
            System.out.println("Setting a tool tip generator for a series overrides the default generator");
        } else {
            System.out.println("Setting a tool tip generator for a series does not override the default generator");
        }
    }

    // Custom category tool tip generator
    public class CustomCategoryToolTipGenerator implements CategoryToolTipGenerator {
        // Implement custom tool tip generation logic
    }

    // Custom XY tool tip generator
    public class CustomXYToolTipGenerator implements XYToolTipGenerator {
        // Implement custom tool tip generation logic
    }
}