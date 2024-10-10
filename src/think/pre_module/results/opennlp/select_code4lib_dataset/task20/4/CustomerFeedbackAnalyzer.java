import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CustomerFeedbackAnalyzer {

    public static Map<String, Double> categorizeCustomerFeedback(String feedbackPath, String modelPath) {
        try {
            // Load the categorizer model
            InputStream modelIn = new FileInputStream(modelPath);
            DoccatModel model = new DoccatModel(modelIn);
            modelIn.close();

            // Initialize DocumentCategorizerME with the loaded model
            DocumentCategorizerME categorizer = new DocumentCategorizerME(model);

            // Read the feedback from the provided path
            ObjectStream<String> lineStream = new PlainTextByLineStream(new FileInputStream(feedbackPath), "UTF-8");
            ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);

            // Categorize the feedback and store the scores in a map
            Map<String, Double> categoryScores = new HashMap<>();
            DocumentSample sample;
            while ((sample = sampleStream.read()) != null) {
                double[] outcomes = categorizer.categorize(sample.getText());
                String category = categorizer.getBestCategory(outcomes);
                categoryScores.put(category, categorizer.getScoreForCategory(outcomes, category));
            }

            return categoryScores;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}