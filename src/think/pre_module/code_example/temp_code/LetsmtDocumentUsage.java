import com.example.LetsmtDocument;

public class LetsmtDocumentUsage {
    public static void main(String[] args) {
        LetsmtDocument letsmtDoc = new LetsmtDocument();
        LetsmtDocument.LetsmtSentence sentence = letsmtDoc.getNonTokenizedText();
        System.out.println(sentence);
    }
}