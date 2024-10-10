import opennlp.tools.ml.maxent.GISTrainer;
import opennlp.tools.ml.maxent.TrainingParameters;

import java.util.Map;

public class GISTrainerExample {

    public static void main(String[] args) {
        TrainingParameters trainingParameters = new TrainingParameters();
        Map<String,String> reportMap = new HashMap<>();

        GISTrainer trainer = new GISTrainer();
        trainer.init(trainingParameters, reportMap);
    }
}