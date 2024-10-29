import opennlp.tools.util.Span;

public class SentenceSampleExample {

    public static void main(String[] args) {
        CharSequence document = "This is a sample document. It has multiple sentences. Hopefully this will work as expected.";
        Span sentence1 = new Span(0, 24);
        Span sentence2 = new Span(26, 49);

        SentenceSample sample = new SentenceSample(document, sentence1, sentence2);
    }
}