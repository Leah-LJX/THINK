import opennlp.model.MaxentModel;
import opennlp.model.TrainUtil;
import opennlp.maxent.GISTrainer;
import opennlp.model.AbstractModel;

public class MaxentModelExample {

  public static void main(String[] args) {
    DataIndexer indexer = new DataIndexer();
    // initialize indexer

    GISTrainer trainer = new GISTrainer();
    // initialize trainer

    MaxentModel model = trainer.doTrain(indexer);
  }
}