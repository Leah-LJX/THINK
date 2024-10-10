import org.jfree.chart.PublicCloneable;

public class MyClass implements PublicCloneable {

    public void testPublicCloneable() {
        // Verify that this class implements PublicCloneable
        if (this instanceof PublicCloneable) {
            System.out.println("This class implements PublicCloneable");
        } else {
            System.out.println("This class does not implement PublicCloneable");
        }
    }
}