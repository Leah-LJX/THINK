import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;
import opennlp.tools.util.model.ModelUtil;

public class NERModelTrainer {

    public static void trainNERModelForFinance(String trainingDataPath, String modelOutputPath) {
        InputStream trainingDataInputStream = null;
        ObjectStream<String> lineStream = null;
        ObjectStream<NameSample> sampleStream = null;
        FileOutputStream modelOutputStream = null;

        try {
            // Open the training data file
            trainingDataInputStream = new FileInputStream(new File(trainingDataPath));
            Reader reader = new InputStreamReader(trainingDataInputStream, StandardCharsets.UTF_8);
            lineStream = new PlainTextByLineStream(reader);
            sampleStream = new NameSampleDataStream(lineStream);

            // Set training parameters
            TrainingParameters params = ModelUtil.createDefaultTrainingParameters();

            // Train the model
            TokenNameFinderModel model = NameFinderME.train("en", "finance", sampleStream, params, null);

            // Save the model to a file
            modelOutputStream = new FileOutputStream(new File(modelOutputPath));
            model.serialize(modelOutputStream);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (sampleStream != null) {
                    sampleStream.close();
                }
                if (lineStream != null) {
                    lineStream.close();
                }
                if (trainingDataInputStream != null) {
                    trainingDataInputStream.close();
                }
                if (modelOutputStream != null) {
                    modelOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java NERModelTrainer <trainingDataPath> <modelOutputPath>");
            System.exit(1);
        }

        String trainingDataPath = args[0];
        String modelOutputPath = args[1];

        trainNERModelForFinance(trainingDataPath, modelOutputPath);
    }
}