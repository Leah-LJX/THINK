import opennlp.maxent.MaxentModel;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.parser.ParserType;
import opennlp.tools.postag.POSModel;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.parser.chunking.Parser;

import java.util.Map;

public class ParserModelExample {

    public static void main(String[] args) {
        String languageCode = "en";
        MaxentModel buildModel = new MaxentModel();
        MaxentModel checkModel = new MaxentModel();
        POSModel parserTagger = new POSModel();
        ChunkerModel chunkerTagger = new ChunkerModel();
        HeadRules headRules = new HeadRules();
        ParserType type = ParserType.CHUNKING;
        Map<String, String> manifestInfoEntries = new HashMap<>();

        ParserModel parserModel = new ParserModel(languageCode, buildModel, checkModel, parserTagger, chunkerTagger, headRules, type, manifestInfoEntries);
    }
}