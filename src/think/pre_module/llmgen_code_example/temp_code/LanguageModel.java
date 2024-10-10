import java.util.Arrays;

public class LanguageModel {
    public static void main(String[] args) {
        LanguageModel lm = new LanguageModel();
        String[] inputTokens = {"The", "quick", "brown"};
        
        String[] predictedTokens = lm.predictNextTokens(inputTokens);
        
        System.out.println(Arrays.toString(predictedTokens));
    }

    String[] predictNextTokens(String... tokens) {
        // Implementation here
        return new String[]{"fox", "jumps", "over"};
    }
}