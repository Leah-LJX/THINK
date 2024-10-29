import opennlp.tools.doccat.DocumentCategorizerME;

public class CategorizerExample {
    public static void main(String[] args) {
        String[] text = {"The", "cat", "is", "on", "the", "mat"};
        DocumentCategorizerME documentCategorizer = new DocumentCategorizerME();
        double[] result = documentCategorizer.categorize(text);
    }
}