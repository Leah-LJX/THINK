import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;
import opennlp.tools.util.model.ModelUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SentimentAnalysisModel {

    public static void trainSentimentModel(String trainingFilePath, String modelOutputPath) {
        try {
            // Load training data
            ObjectStream<String> lineStream = new PlainTextByLineStream(
                    new FileInputStream(trainingFilePath), StandardCharsets.UTF_8);
            ObjectStream<SentenceSample> sampleStream = new SentenceSampleStream(lineStream);

            // Set training parameters
            TrainingParameters params = ModelUtil.createDefaultTrainingParameters();
            params.put(TrainingParameters.CUTOFF_PARAM, "1");

            // Train the sentence model
            SentenceModel sentModel = SentenceDetectorME.train("en", sampleStream, params, new SentenceDetectorFactory());

            // Save the trained model to a file
            FileOutputStream modelOut = new FileOutputStream(new File(modelOutputPath));
            sentModel.serialize(modelOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}