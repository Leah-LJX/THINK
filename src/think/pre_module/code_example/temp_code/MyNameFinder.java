import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

public class MyNameFinder {
    public static void main(String[] args) {
        String[] tokenizedText = {"John", "Smith", "is", "a", "software", "engineer"};
        DictionaryNameFinder nameFinder = new DictionaryNameFinder();

        Span[] nameSpans = nameFinder.find(tokenizedText);
    }
}