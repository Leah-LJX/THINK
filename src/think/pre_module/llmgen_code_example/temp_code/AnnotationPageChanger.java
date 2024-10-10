import com.example.pdf.FDFAnnotation;

public class AnnotationPageChanger {
    public static void main(String[] args) {
        FDFAnnotation annotation = new FDFAnnotation();
        int pageNumber = 5;
        annotation.setPage(pageNumber);
    }
}