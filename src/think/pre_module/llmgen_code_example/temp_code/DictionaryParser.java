import org.apache.pdfbox.cos.COSDictionary;

public class DictionaryParser extends BaseParser {
    public boolean isDirect;

    public DictionaryParser(boolean isDirect) {
        this.isDirect = isDirect;
    }

    public COSDictionary parseDictionary() {
        return parseCOSDictionary(isDirect);
    }
}