import org.jfree.chart.JThermometer;

public class ThermometerAxis {
    public void setThermometerAxisZoom(boolean flag) {
        JThermometer.setFollowDataInSubranges(flag);
    }
}