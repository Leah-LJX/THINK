import org.jfree.chart.ChartPanel;

public class ChartTitleManager {
    public ChartPanel chartPanel;

    public ChartTitleManager(ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }

    public void setURLTextAndNotifyListeners(String text) {
        chartPanel.setURLText(text);
    }
}