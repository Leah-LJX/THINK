import opennlp.tools.doccat.DoccatFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

import java.io.*;

public class BlogCategorizer {

    public static void categorizeBlogPosts(String blogsPath, String modelOutputPath) {
        try {
            // Load and preprocess the data
            InputStreamFactory inputStreamFactory = new InputStreamFactory() {
                public InputStream createInputStream() throws IOException {
                    return new FileInputStream(blogsPath);
                }
            };

            ObjectStream<String> lineStream = new PlainTextByLineStream(inputStreamFactory, "UTF-8");
            ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);

            // Train the model
            TrainingParameters params = new TrainingParameters();
            params.put(TrainingParameters.ITERATIONS_PARAM, 100);
            params.put(TrainingParameters.CUTOFF_PARAM, 1);

            DoccatModel model = DocumentCategorizerME.train("en", sampleStream, params, new DoccatFactory());

            // Save the trained model
            try (OutputStream modelOut = new BufferedOutputStream(new FileOutputStream(modelOutputPath))) {
                model.serialize(modelOut);
            }

            System.out.println("Model training and saving completed successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java BlogCategorizer <blogsPath> <modelOutputPath>");
            return;
        }

        String blogsPath = args[0];
        String modelOutputPath = args[1];

        categorizeBlogPosts(blogsPath, modelOutputPath);
    }
}