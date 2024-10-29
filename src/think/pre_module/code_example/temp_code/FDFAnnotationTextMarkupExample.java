import java.awt.Color;

public class FDFAnnotationTextMarkupExample {
    public static void main(String[] args) {
        // Initialize variables
        String author = "John Doe";
        String contents = "This is a text markup annotation";
        Color color = new Color(255, 0, 0); // red color
        int pageNum = 1;
        
        // Create FDFAnnotationTextMarkup instance
        FDFAnnotationTextMarkup textMarkup = new FDFAnnotationTextMarkup(author, contents, color, pageNum);
        
        // Use FDFAnnotationTextMarkup methods
        textMarkup.setContents("Updated text markup annotation");
        System.out.println("Author: " + textMarkup.getAuthor());
        System.out.println("Contents: " + textMarkup.getContents());
        System.out.println("Color: " + textMarkup.getColor());
        System.out.println("Page number: " + textMarkup.getPageNumber());
    }
}