import com.example.pdf.FDFAnnotation;

public class AnnotationUsage {

    public static void main(String[] args) {
        FDFAnnotation annotation = new FDFAnnotation();
        boolean readOnly = annotation.isReadOnly();
        System.out.println("Is read only: " + readOnly);
    }
}