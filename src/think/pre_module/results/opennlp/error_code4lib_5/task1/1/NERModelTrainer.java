import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;
import opennlp.tools.util.Span;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class NERModelTrainer {

    public static void trainNERModelForFinance(String trainingDataPath, String modelOutputPath) {
        try {
            InputStreamFactory dataIn = new MarkableFileInputStreamFactory(new File(trainingDataPath));
            ObjectStream<String> lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
            ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream);

            TokenNameFinderModel model;

            TrainingParameters params = TrainingParameters.defaultParams();
            model = NameFinderME.train("en", "finance", sampleStream, params, TokenNameFinderModel.MAXENT_SEQUENCE_MODEL, null);

            FileOutputStream modelOut = new FileOutputStream(modelOutputPath);
            model.serialize(modelOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}