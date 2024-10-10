import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionURI;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PDFHyperlinkExtractor {

    public static void extractHyperlinks(String pdfFilePath, String csvOutputPath) {
        try (PDDocument document = PDDocument.load(new File(pdfFilePath));
             FileWriter csvWriter = new FileWriter(csvOutputPath)) {

            csvWriter.append("Page Number,Hyperlink\n");
            
            int pageCount = document.getNumberOfPages();
            for (int i = 0; i < pageCount; i++) {
                List<PDAnnotation> annotations = document.getPage(i).getAnnotations();
                for (PDAnnotation annotation : annotations) {
                    if (annotation instanceof PDAnnotationLink) {
                        PDAnnotationLink link = (PDAnnotationLink) annotation;
                        if (link.getAction() instanceof PDActionURI) {
                            PDActionURI uri = (PDActionURI) link.getAction();
                            String url = uri.getURI();
                            csvWriter.append(String.valueOf(i + 1)).append(",").append(url).append("\n");
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("An error occurred while processing the PDF: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String pdfFilePath = "example.pdf"; // Replace with your PDF file path
        String csvOutputPath = "output.csv"; // Replace with your desired CSV output file path
        extractHyperlinks(pdfFilePath, csvOutputPath);
    }
}