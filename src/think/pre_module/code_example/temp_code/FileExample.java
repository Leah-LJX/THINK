import some.package.PDComplexFileSpecification;

public class FileExample {
    public static void main(String[] args) {
        PDComplexFileSpecification fileSpec = new PDComplexFileSpecification();
        String fileName = fileSpec.getFile();
        System.out.println("File name: " + fileName);
    }
}