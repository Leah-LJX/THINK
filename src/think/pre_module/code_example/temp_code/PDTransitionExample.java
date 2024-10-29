import org.apache.pdfbox.pdmodel.interactive.documentnavigation.transition.PDTransition;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.transition.PDTransitionDimension;

public class PDTransitionExample {
    public static void main(String[] args) {
        PDTransition transition = new PDTransition();
        PDTransitionDimension dimension = PDTransitionDimension.H;
        
        transition.setDimension(dimension);
    }
}