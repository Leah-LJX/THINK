import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.PlainTextByLineStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmotionAnalyzer {

    public static Map<String, Integer> analyzeSocialMediaEmotions(String postsPath, String modelPath) {
        Map<String, Integer> emotionCounts = new HashMap<>();
        DoccatModel model = null;

        // Load the model
        try (InputStream modelStream = new FileInputStream(modelPath)) {
            model = new DoccatModel(modelStream);
        } catch (IOException e) {
            e.printStackTrace();
            return emotionCounts;  // Return an empty map in case of error
        }

        DocumentCategorizerME categorizer = new DocumentCategorizerME(model);

        // Read and categorize each post
        try {
            List<String> lines = Files.readAllLines(Paths.get(postsPath));
            for (String post : lines) {
                double[] outcomes = categorizer.categorize(new String[]{post}); // Pass the post as a single-element array
                String category = categorizer.getBestCategory(outcomes);

                // Update the counts in the map
                emotionCounts.put(category, emotionCounts.getOrDefault(category, 0) + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return emotionCounts;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java EmotionAnalyzer <posts-file-path> <model-file-path>");
            return;
        }

        String postsPath = args[0];
        String modelPath = args[1];

        Map<String, Integer> emotionCounts = analyzeSocialMediaEmotions(postsPath, modelPath);
        System.out.println("Emotion Counts: " + emotionCounts);
    }
}