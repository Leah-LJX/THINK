import com.example.QNMinimizer;

public class MinimizerExample {
    public static void main(String[] args) {
        double[] parameters = {1.0, 2.0, 3.0};
        QNMinimizer.Evaluator evaluator = new QNMinimizer.Evaluator();
        double result = evaluator.evaluate(parameters);
        System.out.println("Quality of training parameters: " + result);
    }
}