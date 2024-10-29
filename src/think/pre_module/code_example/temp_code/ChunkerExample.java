import java.io.FileInputStream;
import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.util.Span;

public class ChunkerExample {

    public static void main(String[] args) {
        try {
            FileInputStream modelIn = new FileInputStream("en-chunker.bin");
            ChunkerModel model = new ChunkerModel(modelIn);
            ChunkerME chunker = new ChunkerME(model);

            String[] sentence = new String[]{"Rocky", "wants", "to", "play", "soccer"};
            String[] tags = new String[]{"NNP", "VBZ", "TO", "VB", "NN"};
            Span[] chunks = chunker.chunkAsSpans(sentence, tags);

            for (Span s : chunks) {
                System.out.println(s);
            }

            modelIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}