import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationFileAttachment;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDTargetDirectory;

public class PDTargetDirectoryExample {
    public static void main(String[] args) {
        PDAnnotationFileAttachment fileAttachment = new PDAnnotationFileAttachment();
        PDTargetDirectory targetDirectory = new PDTargetDirectory(fileAttachment);
        int pageNumber = targetDirectory.getPageNumber();
        System.out.println("Page number containing the file attachment: " + pageNumber);
    }
}