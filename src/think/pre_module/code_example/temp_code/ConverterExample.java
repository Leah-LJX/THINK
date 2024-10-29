import java.lang.reflect.Constructor;

public class ConverterExample {
    public static void main(String[] args) {
        Converter converter = new Converter();
        Class<?> supportedType = converter.getSupportedType();
        System.out.println("Supported type: " + supportedType);
    }
}