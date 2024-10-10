import org.jfree.chart.LegendItem;

public class LegendItemExample {
    public static void main(String[] args) {
        // Create a LegendItem object
        LegendItem originalItem = new LegendItem("Series 1", "Description", "ToolTip", "URL", true, new Color(255, 0, 0));
        
        // Clone the LegendItem
        LegendItem clonedItem = originalItem.clone();
        
        // Output the cloned item
        System.out.println("Cloned LegendItem: " + clonedItem.toString());
    }
}