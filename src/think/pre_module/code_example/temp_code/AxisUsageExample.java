import org.jfree.chart.axis.ValueAxis;
import org.jfree.data.Range;

public class AxisUsageExample {
    public static void main(String[] args) {
        ValueAxis axis = new ValueAxis();
        Range range = new Range(0.0, 100.0);
        
        axis.setRange(range);
    }
}