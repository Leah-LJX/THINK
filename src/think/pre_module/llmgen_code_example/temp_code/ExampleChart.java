import org.jfree.chart.labels.XYToolTipGenerator;

public class ExampleChart {
    
    public XYToolTipGenerator generator = new CustomXYToolTipGenerator();
    public PolarItemRenderer renderer = new PolarItemRenderer();
    
    public void setToolTipGenerator(XYToolTipGenerator customGenerator) {
        renderer.setBaseToolTipGenerator(customGenerator);
    }
}