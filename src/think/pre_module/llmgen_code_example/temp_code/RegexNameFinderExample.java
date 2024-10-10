import opennlp.tools.namefind.RegexNameFinder;

public class RegexNameFinderExample {
    public static void main(String[] args) {
        RegexNameFinder finder = new RegexNameFinder();
        String spanType = finder.getSpanType();
        System.out.println(spanType);
    }
}