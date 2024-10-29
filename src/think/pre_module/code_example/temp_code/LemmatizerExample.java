import some.package.name.DictionaryLemmatizer;

public class LemmatizerExample {
    public static void main(String[] args) {
        DictionaryLemmatizer lemmatizer = new DictionaryLemmatizer();
        
        // Initialize tokens and postags
        String[] tokens = {"I", "am", "running"};
        String[] postags = {"PRP", "VBP", "VBG"};

        // Get the lemmatized result
        String[] lemmas = lemmatizer.lemmatize(tokens, postags);
        
        // Print the lemmatized result
        for (String lemma : lemmas) {
            System.out.println(lemma);
        }
    }
}