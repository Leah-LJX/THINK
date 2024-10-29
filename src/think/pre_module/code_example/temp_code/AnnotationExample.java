import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationAdditionalActions;
import org.apache.pdfbox.pdmodel.interactive.action.PDAction;

public class AnnotationExample {
    public static void main(String[] args) {
        PDAnnotationAdditionalActions annotation = new PDAnnotationAdditionalActions();
        PDAction action = annotation.getPV();
    }
}