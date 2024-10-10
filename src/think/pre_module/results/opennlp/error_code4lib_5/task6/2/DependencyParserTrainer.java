import opennlp.tools.parser.ParserModel;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserType;
import opennlp.tools.parser.chunking.Parser;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.TrainingParameters;
import opennlp.tools.util.model.ModelUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DependencyParserTrainer {

    public static void trainDependencyParser(String trainingDataPath, String modelOutputPath) {
        try {
            ObjectStream<Parse> parseSamples = // create ObjectStream from trainingDataPath
            HeadRules rules = // create HeadRules object
            TrainingParameters mlParams = ModelUtil.createDefaultTrainingParameters();

            ParserModel model = Parser.train("en", parseSamples, rules, mlParams);

            File modelFile = new File(modelOutputPath);
            FileOutputStream modelOut = new FileOutputStream(modelFile);
            model.serialize(modelOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}