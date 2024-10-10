import java.util.List;

public class EntityLinker {
    public List<T> find(String doctext, Span[] sentences, Span[][] tokensBySentence, Span[][] namesBySentence) {
        // API usage example
        // Initialize variables
        String doctext = "Sample document text";
        Span[] sentences = {new Span(0, 10), new Span(11, 20)}; // Example spans
        Span[][] tokensBySentence = {{new Span(0, 3), new Span(4, 7)}, {new Span(0, 4), new Span(5, 10)}}; // Example spans
        Span[][] namesBySentence = {{new Span(0, 3)}, {new Span(5, 10)}}; // Example spans
        
        List<T> result = entityLinker.find(doctext, sentences, tokensBySentence, namesBySentence);
        
        return result;
    }
}