import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotFactory;

public class CloningTest {
    public void testCloning() {
        Plot originalPlot = PlotFactory.createPlot();
        Plot clonedPlot = (Plot) originalPlot.clone();
        
        if (originalPlot != clonedPlot) {
            System.out.println("Cloning works");
        } else {
            System.out.println("Cloning does not work");
        }
    }
}