import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;

public class DictionaryNameFinder {
    public TokenNameFinderModel model;

    public DictionaryNameFinder(TokenNameFinderModel model) {
        this.model = model;
    }

    public Span[] find(String[] textTokenized) {
        NameFinderME nameFinder = new NameFinderME(model);
        return nameFinder.find(textTokenized);
    }
}