import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.entity.ChartEntity;

public class ChartEntityExample {
    public static void main(String[] args) {
        // assume chartMouseEvent is initialized somewhere
        ChartMouseEvent chartMouseEvent = getChartMouseEvent();

        ChartEntity chartEntity = chartMouseEvent.getEntity();
        if(chartEntity != null) {
            // do something with the chart entity
            System.out.println("Chart entity found under mouse point");
        } else {
            System.out.println("No chart entity found under mouse point");
        }
    }

    public static ChartMouseEvent getChartMouseEvent() {
        // implementation to get chartMouseEvent
        return new ChartMouseEvent(null, null, 0, 0);
    }
}