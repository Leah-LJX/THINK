import java.io.File;
    import java.io.FileInputStream;
    import java.io.IOException;
    import java.awt.image.BufferedImage;
    import javax.imageio.ImageIO;
    import org.apache.pdfbox.pdmodel.PDDocument;
    import org.apache.pdfbox.pdmodel.PDPage;
    import org.apache.pdfbox.pdmodel.common.PDStream;
    import org.apache.pdfbox.rendering.PDFRenderer;

    public class PDFCompressor {

        public static void compressMultiplePdfs(String[] pdfFilePaths, String outputDir) {
            for (String pdfFilePath : pdfFilePaths) {
                try {
                    File inputFile = new File(pdfFilePath);
                    String outputFilePath = outputDir + File.separator + inputFile.getName();
                    compressPdf(inputFile, new File(outputFilePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void compressPdf(File inputFile, File outputFile) throws IOException {
            try (PDDocument document = PDDocument.load(inputFile)) {
                PDFRenderer pdfRenderer = new PDFRenderer(document);
                PDDocument compressedDocument = new PDDocument();

                for (int page = 0; page < document.getNumberOfPages(); ++page) {
                    BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 150); // Reduce DPI to 150 for compression
                    PDPage newPage = new PDPage(document.getPage(page).getMediaBox());
                    compressedDocument.addPage(newPage);

                    File tempImageFile = File.createTempFile("temp", ".jpg");
                    ImageIO.write(bim, "jpg", tempImageFile);

                    try (FileInputStream fis = new FileInputStream(tempImageFile)) {
                        PDStream pdStream = new PDStream(compressedDocument, fis);
                        newPage.setContents(pdStream);
                    }

                    tempImageFile.delete();
                }

                compressedDocument.save(outputFile);
                compressedDocument.close();
            }
        }

        public static void main(String[] args) {
            String[] pdfFilePaths = {"example1.pdf", "example2.pdf"};
            String outputDir = "compressed_pdfs";
            compressMultiplePdfs(pdfFilePaths, outputDir);
        }
    }