import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationText;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationTextMarkup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PDFAnnotationExtractor {

    public static void extractAnnotations(String pdfFilePath, String outputFilePath) {
        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
            
            int pageNumber = 0;
            for (PDPage page : document.getPages()) {
                pageNumber++;
                List<PDAnnotation> annotations = page.getAnnotations();
                
                for (PDAnnotation annotation : annotations) {
                    String annotationText = "";
                    
                    if (annotation instanceof PDAnnotationText) {
                        annotationText = ((PDAnnotationText) annotation).getContents();
                    } else if (annotation instanceof PDAnnotationTextMarkup) {
                        annotationText = ((PDAnnotationTextMarkup) annotation).getContents();
                    }
                    
                    if (annotationText != null && !annotationText.trim().isEmpty()) {
                        writer.write("Page " + pageNumber + ": " + annotationText);
                        writer.newLine();
                    }
                }
            }
            
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage
        extractAnnotations("path/to/your/input.pdf", "path/to/your/output.txt");
    }
}