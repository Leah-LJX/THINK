import org.jfree.chart.*;

public class CloningTest {
    public void testCloning() {
        // Assuming we have an object to clone
        Object originalObject = new Object();

        // Using the clone method to create a clone of the object
        Object clonedObject = originalObject.clone();

        // Performing some checks on the cloned object
        if (originalObject != clonedObject) {
            System.out.println("Cloning successful: The cloned object is independent from the original object.");
        } else {
            System.out.println("Cloning failed: The cloned object is not independent from the original object.");
        }
    }
}