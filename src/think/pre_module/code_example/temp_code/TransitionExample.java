import org.apache.pdfbox.pdmodel.interactive.documentnavigation.page.PDTransition;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.page.PDTransitionDirection;

public class TransitionExample {
    public static void main(String[] args) {
        PDTransition transition = new PDTransition();
        
        PDTransitionDirection direction = PDTransitionDirection.LEFT_TO_RIGHT;
        
        transition.setDirection(direction);
    }
}