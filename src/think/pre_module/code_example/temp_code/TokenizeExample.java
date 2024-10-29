import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

public class TokenizeExample {

    public static void main(String[] args) {
        String sentence = "A sentence to be tokenized.";
        
        // Load tokenizer model
        TokenizerModel model = new TokenizerModel(new File("en-token.bin"));
        
        // Initialize TokenizerME
        TokenizerME tokenizer = new TokenizerME(model);
        
        // Tokenize the sentence
        String[] tokens = tokenizer.tokenize(sentence);
        
        // Print the tokens
        for (String token : tokens) {
            System.out.println(token);
        }
    }
}