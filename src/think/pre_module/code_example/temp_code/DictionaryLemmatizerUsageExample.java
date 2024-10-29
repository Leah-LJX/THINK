import java.util.List;

public class DictionaryLemmatizerUsageExample {
    public static void main(String[] args) {
        List<String> tokens = List.of("running", "ate", "cats");
        List<String> posTags = List.of("VBG", "VBD", "NNS");
        
        DictionaryLemmatizer lemmatizer = new DictionaryLemmatizer();
        List<List<String>> lemmas = lemmatizer.lemmatize(tokens, posTags);
        
        for (List<String> lemma : lemmas) {
            System.out.println("Lemma tags: " + lemma);
        }
    }
}