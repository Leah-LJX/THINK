import org.jfree.data.KeyedValues;
import org.jfree.data.DefaultKeyedValues;

public class KeyedValuesExample {

    public static void main(String[] args) {
        KeyedValues keyedValues = new DefaultKeyedValues();
        
        keyedValues.setValue("A", 100);
        keyedValues.setValue("B", 200);
        keyedValues.setValue("C", 300);
        
        System.out.println(keyedValues.getValue("B")); // Output: 200
    }
}