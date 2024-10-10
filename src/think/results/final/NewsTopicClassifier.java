import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.PlainTextByLineStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class NewsTopicClassifier {

    public static Map<String, Double> classifyNewsTopics(String newsArticlesPath, String modelPath) {
        Map<String, Double> topicScores = new HashMap<>();
        
        try (InputStream modelIn = new FileInputStream(modelPath)) {
            DoccatModel model = new DoccatModel(modelIn);
            DocumentCategorizerME categorizer = new DocumentCategorizerME(model);

            try (Stream<String> lines = Files.lines(Paths.get(newsArticlesPath))) {
                lines.forEach(line -> {
                    // Convert the line into a String array
                    String[] doc = new String[]{line};
                    double[] outcomes = categorizer.categorize(doc);
                    String category = categorizer.getBestCategory(outcomes);

                    topicScores.merge(category, 1.0, Double::sum);
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Normalize the scores to get probabilities
        double total = topicScores.values().stream().mapToDouble(Double::doubleValue).sum();
        topicScores.replaceAll((k, v) -> v / total);

        return topicScores;
    }
    
    public static void main(String[] args) {
        // Test the method with example paths
        String newsArticlesPath = "path/to/news/articles.txt";
        String modelPath = "path/to/en-doccat.bin";

        Map<String, Double> topicDistribution = classifyNewsTopics(newsArticlesPath, modelPath);
        topicDistribution.forEach((topic, score) -> System.out.println(topic + ": " + score));
    }
}