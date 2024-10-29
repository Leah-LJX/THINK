import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDTransition;

public class PDPageExample {
    public static void main(String[] args) {
        PDPage page = new PDPage();
        PDTransition transition = new PDTransition();
        float duration = 1.5f;
        
        page.setTransition(transition, duration);
    }
}