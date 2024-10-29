import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.parser.chunking.Parser;
import opennlp.tools.parser.chunking.ParserEventStream;
import opennlp.tools.parser.chunking.ParserME;
import opennlp.tools.parser.chunking.ParserModel;
import opennlp.tools.parser.lang.en.HeadRules;
import opennlp.tools.parser.lang.en.parser.Parser;
import opennlp.tools.util.ObjectStream;

public class ParserExample {
    public static void main(String[] args) {
        String languageCode = "en";
        ObjectStream<Parse> parseSamples = null; // initialize with actual parse samples
        HeadRules rules = null; // initialize with actual head rules
        int iterations = 100;
        int cutoff = 5;

        ParserModel model = Parser.train(languageCode, parseSamples, rules, iterations, cutoff);
    }
}