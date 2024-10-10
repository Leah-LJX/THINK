import org.jfree.chart.PublicCloneable;

public class MyClass implements PublicCloneable {

    public void testPublicCloneable() {
        if (this.clone() instanceof MyClass) {
            System.out.println("This class implements PublicCloneable");
        } else {
            System.out.println("This class does not implement PublicCloneable");
        }
    }
}