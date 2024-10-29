import org.jfree.chart.axis.Axis;

public class ChartPlot {
    public Axis axis;

    public void setAxis(Axis axis) {
        this.axis = axis;
    }

    public void configureAxis() {
        if (axis != null) {
            axis.configure();
        }
    }
}