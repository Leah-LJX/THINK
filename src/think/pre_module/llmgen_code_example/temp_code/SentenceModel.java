import opennlp.maxent.MaxentModel;
import opennlp.tools.sentdetect.SentenceDetectorFactory;

import java.util.Map;

public class SentenceModel {
    String languageCode;
    MaxentModel sentModel;
    Map<String, String> manifestInfoEntries;
    SentenceDetectorFactory sdFactory;

    public SentenceModel(String languageCode, MaxentModel sentModel, Map<String, String> manifestInfoEntries, SentenceDetectorFactory sdFactory) {
        this.languageCode = languageCode;
        this.sentModel = sentModel;
        this.manifestInfoEntries = manifestInfoEntries;
        this.sdFactory = sdFactory;
    }
}

// Example usage
MaxentModel sentModel = // initialize maxent model
Map<String, String> manifestInfoEntries = // initialize manifest info entries
SentenceDetectorFactory sdFactory = // initialize sentence detector factory
String languageCode = "en"; // initialize language code

SentenceModel model = new SentenceModel(languageCode, sentModel, manifestInfoEntries, sdFactory);