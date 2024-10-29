import some.package.FDFAnnotation;

public class AnnotationPrinter {
    public static void main(String[] args) {
        FDFAnnotation annotation = new FDFAnnotation();
        boolean printed = annotation.isPrinted();
        System.out.println("Is printed: " + printed);
    }
}