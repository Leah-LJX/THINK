import org.apache.pdfbox.cos.COSArrayList;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;

public class COSArrayListExample {
    public static void main(String[] args) {
        COSDictionary dictionary = new COSDictionary();
        COSName dictionaryKey = COSName.getPDFName("array");
        COSArrayList arrayList = new COSArrayList(dictionary, dictionaryKey);
    }
}