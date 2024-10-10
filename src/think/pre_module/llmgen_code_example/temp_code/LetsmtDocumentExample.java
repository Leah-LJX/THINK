import LetsmtDocument.LetsmtSentence;

public class LetsmtDocumentExample {
    public static void main(String[] args) {
        LetsmtDocument letsmtDocument = new LetsmtDocument();
        LetsmtSentence[] tokens = letsmtDocument.getTokens();
    }
}