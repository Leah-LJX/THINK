import java.util.List;

public class LemmaGenerator {
    public static void main(String[] args) {
        List<String> tokens = List.of("walk", "running", "swimming");
        List<String> posTags = List.of("VB", "VBG", "VBG");
        
        DictionaryLemmatizer lemmatizer = new DictionaryLemmatizer();
        List<List<String>> lemmas = lemmatizer.lemmatize(tokens, posTags);
        
        System.out.println(lemmas);
    }
}