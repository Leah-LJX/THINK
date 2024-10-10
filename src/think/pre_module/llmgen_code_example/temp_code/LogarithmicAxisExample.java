import org.jfree.chart.axis.LogarithmicAxis;

public class LogarithmicAxisExample {
    public static void main(String[] args) {
        LogarithmicAxis axis = new LogarithmicAxis("Value");
        //... other axis configuration
        axis.autoAdjustRange();
    }
}