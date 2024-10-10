import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;

public class PDAnnotationUsageExample {
    public static void main(String[] args) {
        PDAnnotation annotation = new PDAnnotation();
        boolean hidden = true;
        
        annotation.setHidden(hidden);
    }
}