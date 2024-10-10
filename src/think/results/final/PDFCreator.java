import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PDFCreator {

    public static void createPdfFromImages(String[] imagePaths, String pdfOutputPath, Map<String, String> metadata) {
        PDDocument document = new PDDocument();
        try {
            for (String imagePath : imagePaths) {
                PDImageXObject image = PDImageXObject.createFromFile(imagePath, document);
                PDRectangle rectangle = new PDRectangle(image.getWidth(), image.getHeight());
                PDPage page = new PDPage(rectangle);
                document.addPage(page);
                try (PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.OVERWRITE, true, true)) {
                    contentStream.drawImage(image, 0, 0, image.getWidth(), image.getHeight());
                }
            }
            if (metadata != null && !metadata.isEmpty()) {
                PDDocumentInformation info = document.getDocumentInformation();
                if (metadata.containsKey("Title")) {
                    info.setTitle(metadata.get("Title"));
                }
                if (metadata.containsKey("Author")) {
                    info.setAuthor(metadata.get("Author"));
                }
                // Set other metadata if needed
            }
            document.save(pdfOutputPath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String[] imagePaths = {"path/to/image1.jpg", "path/to/image2.jpg"};
        String pdfOutputPath = "path/to/output.pdf";
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Title", "My PDF Title");
        metadata.put("Author", "Author Name");

        createPdfFromImages(imagePaths, pdfOutputPath, metadata);
    }
}