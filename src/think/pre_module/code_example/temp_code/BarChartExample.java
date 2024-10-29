import org.jfree.chart.renderer.category.BarRenderer;

public class BarChartExample {
    public static void main(String[] args) {
        BarRenderer renderer = new BarRenderer();
        double maximumBarWidth = renderer.getMaximumBarWidth();
        System.out.println("Maximum bar width: " + maximumBarWidth);
    }
}