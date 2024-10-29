import opennlp.tools.ml.model.AbstractDataIndexer;
import opennlp.tools.ml.model.Event;
import opennlp.tools.ml.model.Sequence;
import opennlp.tools.ml.model.SequenceStream;
import opennlp.tools.ml.model.SequenceTrainer;
import opennlp.tools.util.ObjectStream;

public class SimplePerceptronSequenceTrainer extends AbstractEventModelSequenceTrainer {

  public SimplePerceptronSequenceTrainer(boolean useAverage) {
    super(useAverage);
  }

  @Override
  public void train(SequenceStream events, int iterations) {
    // implementation here
  }
  
  @Override
  public void train(SequenceStream events) {
    // implementation here
  }
  
  @Override
  public void train(ObjectStream<Event> events, int iterations, int cut) {
    // implementation here
  }
  
  @Override
  public void train(SequenceStream events, int iterations, int cut) {
    // implementation here
  }

}