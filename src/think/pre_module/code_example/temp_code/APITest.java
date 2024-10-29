import LetsmtDocument.LetsmtSentence;

public class APITest {
    public static void main(String[] args) {
        LetsmtSentence sentence = new LetsmtSentence();
        String nonTokenizedText = sentence.getNonTokenizedText();
        System.out.println(nonTokenizedText);
    }
}