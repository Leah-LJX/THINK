import org.jfree.chart.*;

public class ThermometerAxisZoom {
    public void setFollowDataInSubranges(boolean flag) {
        // Call the API method to set the flag
        ThermometerPlot plot = new ThermometerPlot();
        plot.setFollowDataInSubranges(flag);
    }
}