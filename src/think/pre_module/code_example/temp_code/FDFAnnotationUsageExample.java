import com.itextpdf.kernel.pdf.annot.FDFAnnotation;

public class FDFAnnotationUsageExample {
    public static void main(String[] args) {
        FDFAnnotation fdfAnnotation = new FDFAnnotation();
        
        boolean lockedContents = fdfAnnotation.isLockedContents();
    }
}