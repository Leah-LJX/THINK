import opennlp.tools.util.Span;

public class ChunkerUsageExample {
    public static void main(String[] args) {
        String[] toks = {"She", "enjoys", "playing", "tennis"};
        String[] tags = {"PRP", "VBZ", "VBG", "NN"};

        Chunker chunker = new Chunker();
        Span[] result = chunker.chunkAsSpans(toks, tags);

        for (Span span : result) {
            System.out.println(span.toString());
        }
    }
}