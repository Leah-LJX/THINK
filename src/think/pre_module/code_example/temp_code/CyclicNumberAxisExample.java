import org.jfree.chart.axis.CyclicNumberAxis;

public class CyclicNumberAxisExample {
    public static void main(String[] args) {
        CyclicNumberAxis axis = new CyclicNumberAxis();

        double cycleBound = axis.getCycleBound();
        System.out.println("Cycle Bound: " + cycleBound);
    }
}