import opennlp.tools.doccat.DocumentCategorizerME;

import java.util.Map;

public class DocumentCategorizerExample {
    public static void main(String[] args) {
        String[] text = {"This", "is", "a", "test"};
        Map<String, Object> extraInformation = null;

        DocumentCategorizerME documentCategorizer = new DocumentCategorizerME();
        double[] result = documentCategorizer.categorize(text, extraInformation);
    }
}