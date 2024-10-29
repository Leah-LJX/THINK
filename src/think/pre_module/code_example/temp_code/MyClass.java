import org.jfree.chart.plot.Plot;
import your.package.DefaultPolarPlotEditor;

public class MyClass {
    public static void main(String[] args) {
        Plot myPlot = ...; // initialize your plot
        DefaultPolarPlotEditor editor = new DefaultPolarPlotEditor();
        editor.updatePlotProperties(myPlot);
    }
}