import org.jfree.chart.axis.PeriodAxis;

public class AxisConfigurator {
    public PeriodAxis axis;

    public AxisConfigurator(PeriodAxis axis) {
        this.axis = axis;
    }

    public void configureAxis() {
        axis.configure();
    }

    public static void main(String[] args) {
        PeriodAxis periodAxis = new PeriodAxis("Time");
        AxisConfigurator configurator = new AxisConfigurator(periodAxis);
        configurator.configureAxis();
    }
}