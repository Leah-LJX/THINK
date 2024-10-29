import opennlp.tools.util.*;
import opennlp.tools.ml.model.*;
import opennlp.tools.postag.*;

public class EventGenerator {

  public static void main(String[] args) {
    String[] sentence = {"The", "quick", "brown", "fox", "jumped", "over", "the", "lazy", "dog"};
    String[] tags = {"DT", "JJ", "NN", "NN", "VBD", "IN", "DT", "JJ", "NN"};
    Object[] additionalContext = null;
    POSContextGenerator cg = new DefaultPOSContextGenerator();
    
    List<Event> events = POSSampleEventStream.generateEvents(sentence, tags, additionalContext, cg);
  }
}