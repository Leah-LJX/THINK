import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationFileAttachment;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.interactive.pdmodel.interactive.annotation.PDFileSpecification;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MediaExtractor {
    public static void extractMediaFromPdf(String pdfFilePath, String mediaOutputDir) {
        PDDocument document = null;
        try {
            document = PDDocument.load(new File(pdfFilePath));
            List<PDPage> pages = document.getDocumentCatalog().getPages();

            for (PDPage page : pages) {
                List<PDAnnotation> annotations = page.getAnnotations();
                for (PDAnnotation annotation : annotations) {
                    if (annotation instanceof PDAnnotationFileAttachment) {
                        PDAnnotationFileAttachment fileAttachment = (PDAnnotationFileAttachment) annotation;
                        PDFileSpecification fileSpec = fileAttachment.getFile();
                        PDStream fileStream = fileSpec.getEmbeddedFile();

                        // Check if the attachment is a media file
                        String fileName = fileSpec.getFile();
                        if (fileName.endsWith(".mp3") || fileName.endsWith(".mp4") || fileName.endsWith(".wav")) {
                            // Save the media file
                            InputStream inputStream = fileStream.createInputStream();
                            File outputFile = new File(mediaOutputDir, fileName);
                            FileOutputStream outputStream = new FileOutputStream(outputFile);

                            byte[] buffer = new byte[1024];
                            int bytesRead;
                            while ((bytesRead = inputStream.read(buffer)) != -1) {
                                outputStream.write(buffer, 0, bytesRead);
                            }

                            inputStream.close();
                            outputStream.close();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}