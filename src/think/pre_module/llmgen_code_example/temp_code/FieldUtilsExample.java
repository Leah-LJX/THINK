import some.package.FieldUtils;

public class FieldUtilsExample {
    public static void main(String[] args) {
        int value = 15;
        int minValue = 0;
        int maxValue = 10;
        
        int wrappedValue = FieldUtils.getWrappedValue(value, minValue, maxValue);
        System.out.println("Wrapped value: " + wrappedValue);
    }
}