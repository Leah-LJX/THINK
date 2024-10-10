import com.example.FDFAnnotation;

public class AnnotationUtil {
    public static void main(String[] args) {
        FDFAnnotation annotation = new FDFAnnotation();
        boolean noView = annotation.isNoView();
        System.out.println("No view flag: " + noView);
    }
}