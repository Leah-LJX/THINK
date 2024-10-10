import [];
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PdfComparator {

    public static void comparePdfFiles(String pdfFilePath1, String pdfFilePath2, String comparisonOutputPath) {
        try (PDDocument doc1 = PDDocument.load(new File(pdfFilePath1));
             PDDocument doc2 = PDDocument.load(new File(pdfFilePath2));
             PDDocument outputDoc = new PDDocument()) {

            PDFRenderer renderer1 = new PDFRenderer(doc1);
            PDFRenderer renderer2 = new PDFRenderer(doc2);

            int pageCount1 = doc1.getNumberOfPages();
            int pageCount2 = doc2.getNumberOfPages();

            int maxPages = Math.max(pageCount1, pageCount2);

            for (int i = 0; i < maxPages; i++) {
                BufferedImage image1 = i < pageCount1 ? renderer1.renderImageWithDPI(i, 300) : new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
                BufferedImage image2 = i < pageCount2 ? renderer2.renderImageWithDPI(i, 300) : new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

                BufferedImage diffImage = getDifferenceImage(image1, image2);

                PDPage page = new PDPage(PDRectangle.A4);
                outputDoc.addPage(page);

                PDPageContentStream contentStream = new PDPageContentStream(outputDoc, page);
                contentStream.drawImage(BufferedImageRenderer.createFromBufferedImage(outputDoc, diffImage), 0, 0);
                contentStream.close();
            }

            outputDoc.save(comparisonOutputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage getDifferenceImage(BufferedImage img1, BufferedImage img2) {
        int width = Math.max(img1.getWidth(), img2.getWidth());
        int height = Math.max(img1.getHeight(), img2.getHeight());

        BufferedImage diffImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = diffImage.createGraphics();
        g.drawImage(img1, 0, 0, null);
        g.setComposite(AlphaComposite.SrcAtop);
        g.drawImage(img2, 0, 0, null);
        g.setComposite(AlphaComposite.SrcOver);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = img1.getRGB(x, y);
                int rgb2 = img2.getRGB(x, y);
                if (rgb1 != rgb2) {
                    diffImage.setRGB(x, y, Color.RED.getRGB());
                }
            }
        }

        g.dispose();
        return diffImage;
    }

    public static void main(String[] args) {
        String pdfFilePath1 = "path/to/first/pdf";
        String pdfFilePath2 = "path/to/second/pdf";
        String comparisonOutputPath = "path/to/output/pdf";

        comparePdfFiles(pdfFilePath1, pdfFilePath2, comparisonOutputPath);
    }
}