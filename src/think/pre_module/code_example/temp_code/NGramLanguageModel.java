import java.util.Arrays;

public class NGramLanguageModel {
    public String[] predictNextTokens(String... tokens) {
        // Implementation of predictNextTokens method
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        NGramLanguageModel ngramModel = new NGramLanguageModel();
        String[] inputTokens = {"I", "want", "to"};
        String[] predictedTokens = ngramModel.predictNextTokens(inputTokens);
        System.out.println(Arrays.toString(predictedTokens));
    }
}