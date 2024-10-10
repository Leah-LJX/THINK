import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ImageToPdfConverter {

    public static void createPdfFromImages(String[] imagePaths, String pdfOutputPath, Map<String, String> metadata) {
        PDDocument document = new PDDocument();

        try {
            for (String imagePath : imagePaths) {
                File file = new File(imagePath);
                BufferedImage image = ImageIO.read(file);
                PDPage page = new PDPage();
                document.addPage(page);
                PDPageContentStream contentStream = new PDPageContentStream(document, page);
                PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath, document);
                contentStream.drawImage(pdImage, 50, 250);
                contentStream.close();
            }

            if (metadata != null) {
                document.getDocumentInformation().setTitle(metadata.get("title"));
                document.getDocumentInformation().setAuthor(metadata.get("author"));
            }

            document.save(pdfOutputPath);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}