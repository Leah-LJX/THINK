import LetsmtDocument.LetsmtSentence;

public class LetsmtExample {
    public static void main(String[] args) {
        LetsmtDocument letsmtDocument = new LetsmtDocument();
        LetsmtSentence sentence = letsmtDocument.getNonTokenizedText();
    }
}