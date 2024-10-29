import java.util.Map;

public class DocumentCategorizer {
    public double[] categorize(String[] text, Map<String, Object> extraInformation) {
        // Implementation here
        return new double[0];
    }
}

public class Main {
    public static void main(String[] args) {
        DocumentCategorizer categorizer = new DocumentCategorizer();
        String[] text = {"example", "text", "to", "categorize"};
        Map<String, Object> extraInformation = new HashMap<>();
        // Add extra information if needed
        double[] result = categorizer.categorize(text, extraInformation);
        // Further code with the categorization result
    }
}