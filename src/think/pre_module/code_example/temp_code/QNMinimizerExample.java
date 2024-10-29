import com.example.QNMinimizer;

public class QNMinimizerExample {
    public static void main(String[] args) {
        double[] parameters = {0.5, 0.3, 0.8};
        QNMinimizer.Evaluator evaluator = new QNMinimizer.Evaluator();
        double result = evaluator.evaluate(parameters);
        System.out.println("Quality of the training parameters: " + result);
    }
}