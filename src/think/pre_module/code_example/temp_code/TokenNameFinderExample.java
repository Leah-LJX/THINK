import opennlp.tools.namefind.TokenNameFinder;
import opennlp.tools.util.Span;

public class TokenNameFinderExample {

    public static void main(String[] args) {
        TokenNameFinder finder = new TokenNameFinder();

        String[] tokens = { "John", "Smith", "works", "at", "Google" };
        
        Span[] nameSpans = finder.find(tokens);
        
        for (Span span : nameSpans) {
            System.out.println("Name found: " + tokens[span.getStart()]);
        }
    }
}