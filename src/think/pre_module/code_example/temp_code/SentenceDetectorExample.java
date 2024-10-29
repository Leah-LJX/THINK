import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class SentenceDetectorExample {
    public static void main(String[] args) {
        SentenceModel model = ... // initialize with a trained model
        SentenceDetectorME detector = new SentenceDetectorME(model);
        
        // rest of the code to use the sentence detector
        // ...
    }
}