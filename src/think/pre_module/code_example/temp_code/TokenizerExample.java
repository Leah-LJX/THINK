import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.TrainingParameters;

public class TokenizerExample {
    public static void main(String[] args) {
        TokenizerModel model = new TokenizerModel(/* initialize with a trained model or null */);
        
        TokenizerME tokenizer = new TokenizerME(model);
        
        String sentence = "This is a sample sentence to be tokenized.";
        String[] tokens = tokenizer.tokenize(sentence);
        
        for (String token : tokens) {
            System.out.println(token);
        }
    }
}