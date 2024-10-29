import org.jfree.chart.axis.ValueAxis;

public class StandardChartThemeExample {

    public static void main(String[] args) {
        StandardChartTheme theme = new StandardChartTheme();
        ValueAxis axis = new NumberAxis("Example Axis");
        
        theme.applyToValueAxis(axis);
    }
}