import org.apache.pdfbox.cos.COSArray;

public class ShadingType2Example {
    public static void main(String[] args) {
        COSArray newDomain = new COSArray();
        PDShadingType2 shading = new PDShadingType2();
        shading.setDomain(newDomain);
    }
}