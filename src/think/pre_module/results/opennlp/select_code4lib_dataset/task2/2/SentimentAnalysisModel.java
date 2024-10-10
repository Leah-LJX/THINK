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

public class SentimentAnalysisModel {

    public static void trainSentimentModel(String trainingFilePath, String modelOutputPath) {
        try {
            // Load training data
            ObjectStream<String> lineStream = new PlainTextByLineStream(new FileInputStream(trainingFilePath));
            ObjectStream<SentenceSample> sampleStream = new SentenceSampleStream(lineStream);

            // Train sentence detector model
            TrainingParameters mlParams = ModelUtil.createDefaultTrainingParameters();
            SentenceDetectorFactory sdFactory = new SentenceDetectorFactory();
            SentenceModel model = SentenceDetectorME.train("en", sampleStream, sdFactory, mlParams);

            // Save the trained model to the specified output path
            FileOutputStream modelOut = new FileOutputStream(new File(modelOutputPath));
            model.serialize(modelOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}