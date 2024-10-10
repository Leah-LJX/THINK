import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

public class NameFinderExample {
    public static void main(String[] args) {
        String[] tokens = {"John", "Doe", "lives", "in", "New", "York"};

        // Initialize the NameFinderME
        NameFinderME nameFinder = new NameFinderME(new TokenNameFinderModel("en-ner-person.bin"));

        // Find name spans
        Span[] nameSpans = nameFinder.find(tokens);

        // Print the name spans
        for (Span span : nameSpans) {
            System.out.println(tokens[span.getStart()] + " " + tokens[span.getEnd() - 1]);
        }
    }
}