import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;

public class AnnotationModifier {

    public void modifyAnnotationModifiedDate(PDAnnotation annotation, String newModifiedDate) {
        annotation.setModifiedDate(newModifiedDate);
    }

    public static void main(String[] args) {
        PDAnnotation annotation = new PDAnnotation();
        String newModifiedDate = "2022-01-01 12:00:00";
        AnnotationModifier modifier = new AnnotationModifier();
        modifier.modifyAnnotationModifiedDate(annotation, newModifiedDate);
    }
}