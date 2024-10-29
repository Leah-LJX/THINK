import org.jfree.chart.axis.ValueAxis;

public class AxisExample {
    public static void main(String[] args) {
        ValueAxis axis = new ValueAxis();
        boolean autoRange = true;
        axis.setAutoRange(autoRange);
    }
}