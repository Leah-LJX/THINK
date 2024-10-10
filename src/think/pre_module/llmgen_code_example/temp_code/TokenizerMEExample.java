import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.TokenSample;
import opennlp.tools.tokenize.TokenSampleStream;
import opennlp.tools.tokenize.TokenizerFactory;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.TrainingParameters;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

public class TokenizerMEExample {

    public static void main(String[] args) {

        // Initialize the training data
        ObjectStream<String> lineStream = new PlainTextByLineStream(
            TokenizerMEExample.class.getResourceAsStream("training.txt"), "UTF-8");
        
        TokenSampleStream sampleStream = new TokenSampleStream(lineStream);

        // Train a tokenizer model
        TokenizerModel model;
        try {
            model = TokenizerME.train(sampleStream, new TokenizerFactory("en", null, true, null),
                TrainingParameters.defaultParams());
        } finally {
            sampleStream.close();
        }

        // Initialize the tokenizer using the trained model
        TokenizerME tokenizer = new TokenizerME(model);

        // Tokenize a sample text
        String sampleText = "This is a sample sentence to be tokenized.";
        String[] tokens = tokenizer.tokenize(sampleText);

        // Print the tokens
        for (String token : tokens) {
            System.out.println(token);
        }
    }
}