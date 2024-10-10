import opennlp.tools.doccat.DoccatFactory;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SentimentModelTrainer {

    public static void trainSentimentModel(String trainingFilePath, String modelOutputPath) {
        InputStreamFactory inputStreamFactory;
        ObjectStream<String> lineStream;
        ObjectStream<opennlp.tools.doccat.DocumentSample> sampleStream;
        DoccatModel model = null;

        try {
            // Initialize input stream
            inputStreamFactory = new MarkableFileInputStreamFactory(new File(trainingFilePath));
            lineStream = new PlainTextByLineStream(inputStreamFactory, StandardCharsets.UTF_8);
            sampleStream = new DocumentSampleStream(lineStream);

            // Set training parameters
            TrainingParameters params = new TrainingParameters();
            params.put(TrainingParameters.ITERATIONS_PARAM, 100);
            params.put(TrainingParameters.CUTOFF_PARAM, 1);

            // Train the model
            model = DocumentCategorizerME.train("en", sampleStream, params, new DoccatFactory());

            // Save the model to a file
            try (FileOutputStream modelOut = new FileOutputStream(new File(modelOutputPath))) {
                model.serialize(modelOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (sampleStream != null) {
                    sampleStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Example usage
        trainSentimentModel("path/to/training/data.txt", "path/to/output/model.bin");
    }
}