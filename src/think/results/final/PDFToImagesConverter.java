import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PDFToImagesConverter {

    public static void convertPdfToImages(String pdfFilePath, String imagesOutputDir, int resolution) {
        File pdfFile = new File(pdfFilePath);
        if (!pdfFile.exists()) {
            System.err.println("PDF file does not exist: " + pdfFilePath);
            return;
        }

        File outputDir = new File(imagesOutputDir);
        if (!outputDir.exists() && !outputDir.mkdirs()) {
            System.err.println("Failed to create output directory: " + imagesOutputDir);
            return;
        }

        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                // Render the page to an image
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, resolution, ImageType.RGB);

                // Save the image to a file
                String fileName = String.format("page_%03d.png", page + 1);
                File outputFile = new File(outputDir, fileName);
                ImageIO.write(bim, "png", outputFile);
            }
        } catch (IOException e) {
            System.err.println("Error converting PDF to images: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java PDFToImagesConverter <pdfFilePath> <imagesOutputDir> <resolution>");
            System.exit(1);
        }

        String pdfFilePath = args[0];
        String imagesOutputDir = args[1];
        int resolution = Integer.parseInt(args[2]);

        convertPdfToImages(pdfFilePath, imagesOutputDir, resolution);
    }
}