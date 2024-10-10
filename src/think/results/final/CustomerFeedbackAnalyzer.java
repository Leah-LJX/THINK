import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.util.InvalidFormatException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CustomerFeedbackAnalyzer {

    public static Map<String, Double> categorizeCustomerFeedback(String feedbackPath, String modelPath) {
        Map<String, Double> feedbackCategories = new HashMap<>();

        try (InputStream modelInputStream = new FileInputStream(modelPath)) {
            // Load the pre-trained model
            DoccatModel model = new DoccatModel(modelInputStream);
            DocumentCategorizerME categorizer = new DocumentCategorizerME(model);

            // Read the feedback data from the file
            String feedbackData = new String(Files.readAllBytes(Paths.get(feedbackPath)));

            // Tokenize the feedback data
            SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
            String[] tokens = tokenizer.tokenize(feedbackData);

            // Categorize the tokenized feedback data
            double[] outcomes = categorizer.categorize(tokens);
            String bestCategory = categorizer.getBestCategory(outcomes);

            // Add the best category to the feedback categories map
            feedbackCategories.put(bestCategory, categorizer.getAllResults(outcomes).get(bestCategory));

            // Optionally, you can add all categories with their probabilities
            Map<String, Double> allCategories = categorizer.getAllResults(outcomes);
            feedbackCategories.putAll(allCategories);

        } catch (InvalidFormatException e) {
            System.err.println("Invalid format in the model file: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading feedback or model file: " + e.getMessage());
        }

        return feedbackCategories;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java CustomerFeedbackAnalyzer <feedbackPath> <modelPath>");
            return;
        }

        String feedbackPath = args[0];
        String modelPath = args[1];

        Map<String, Double> categorizedFeedback = categorizeCustomerFeedback(feedbackPath, modelPath);

        System.out.println("Categorized Feedback:");
        for (Map.Entry<String, Double> entry : categorizedFeedback.entrySet()) {
            System.out.println("Category: " + entry.getKey() + " - Confidence: " + entry.getValue());
        }
    }
}