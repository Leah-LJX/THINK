import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PDFLinkExtractor {

    public static void extractHyperlinks(String pdfFilePath, String csvOutputPath) {
        try {
            PDDocument document = PDDocument.load(new File(pdfFilePath));
            FileWriter csvWriter = new FileWriter(csvOutputPath);

            for (PDPage page : document.getPages()) {
                List<PDAnnotation> annotations = page.getAnnotations();
                for (PDAnnotation annotation : annotations) {
                    if (annotation instanceof PDAnnotationLink) {
                        PDAnnotationLink link = (PDAnnotationLink) annotation;
                        String url = link.getAction().getURI();
                        csvWriter.append(url).append("\n");
                    }
                }
            }

            csvWriter.flush();
            csvWriter.close();
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}