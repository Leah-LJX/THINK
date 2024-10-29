import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.TrainingParameters;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.tokenize.TokenizerFactory;

public class TokenizationExample {
    public static void main(String[] args) {
        String text = "This is a sample sentence to tokenize.";

        // Loading the token model
        TokenizerModel model = new TokenizerModel("en-token.model");

        // Instantiating the TokenizerME class
        TokenizerME tokenizer = new TokenizerME(model);

        // Tokenizing the given raw text
        String[] tokens = tokenizer.tokenize(text);
        
        // Printing the tokens
        for (String token : tokens) {
            System.out.println(token);
        }
    }
}