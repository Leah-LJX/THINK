import org.apache.pdfbox.pdmodel.graphics.color.PDFunction;
import org.apache.pdfbox.cos.COSArray;

public class PDFunctionExample {
    public static void main(String[] args) {
        COSArray domainValues = new COSArray();
        PDFunction pdFunction = new PDFunction();
        pdFunction.setDomainValues(domainValues);
    }
}