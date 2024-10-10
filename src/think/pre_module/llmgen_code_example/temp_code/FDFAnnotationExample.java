import com.itextpdf.text.pdf.FDFAnnotation;

public class FDFAnnotationExample {
    public static void main(String[] args) {
        FDFAnnotation annotation = new FDFAnnotation();
        boolean lockedContents = annotation.isLockedContents();
        System.out.println("LockedContents: " + lockedContents);
    }
}