import org.jfree.chart.entity.JFreeChartEntity;
import org.jfree.chart.JFreeChart;

public class ChartExample {
    public static void main(String[] args) {
        Shape area = new Rectangle(100, 100, 200, 200); // Example shape
        JFreeChart chart = createChart(); // Example chart creation method
        String toolTipText = "Chart tooltip"; // Example tooltip text

        JFreeChartEntity chartEntity = new JFreeChartEntity(area, chart, toolTipText);
    }

    public static JFreeChart createChart() {
        // method to create JFreeChart
        return new JFreeChart("Chart Title", JFreeChart.DEFAULT_TITLE_FONT, null, true);
    }
}