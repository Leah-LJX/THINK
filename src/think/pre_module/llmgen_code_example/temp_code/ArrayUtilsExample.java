import java.util.Objects;
import org.apache.commons.lang3.ArrayUtils;

public class ArrayUtilsExample {

    public static void main(String[] args) {
        Object[] array1 = { "apple", "banana", "orange" };
        Object[] array2 = { "apple", "banana", "orange" };

        boolean result = ArrayUtils.equalReferencesInArrays(array1, array2);
        System.out.println("Do array1 and array2 have equal references? " + result);
    }
}