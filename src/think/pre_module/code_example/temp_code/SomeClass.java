import opennlp.model.MaxentModel;
import opennlp.tools.sentdetect.SentenceDetectorFactory;
import java.util.Map;

public class SomeClass {
    public static void main(String[] args) {
        String languageCode = "en";
        MaxentModel sentModel = new MaxentModel();
        Map<String, String> manifestInfoEntries = new HashMap<>();
        SentenceDetectorFactory sdFactory = new SentenceDetectorFactory();

        SentenceModel sentenceModel = new SentenceModel(languageCode, sentModel, manifestInfoEntries, sdFactory);
    }
}