import opennlp.tools.util.ObjectStream;
import opennlp.model.Event;
import opennlp.model.EventStream;
import opennlp.model.EventCollector;
import opennlp.model.Sequence;
import opennlp.model.SequenceStream;

public class POSSampleEventStream implements EventStream, SequenceStream {

  public static List<Event> generateEvents(String[] sentence, String[] tags, POSContextGenerator cg) {
    // implementation of the method
  }
}