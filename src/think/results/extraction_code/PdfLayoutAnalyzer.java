import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.text.TextPosition;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PdfLayoutAnalyzer {

    public static void analyzePdfLayout(String pdfFilePath, String reportOutputPath) {
        try (PDDocument document = PDDocument.load(new FileInputStream(pdfFilePath))) {
            PDFTextStripper textStripper = new PDFLayoutTextStripper();
            textStripper.setSortByPosition(true);
            textStripper.setStartPage(0);
            textStripper.setEndPage(document.getNumberOfPages());

            String text = textStripper.getText(document);

            try (FileWriter writer = new FileWriter(reportOutputPath)) {
                writer.write(text);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class PDFLayoutTextStripper extends PDFTextStripper {

        public PDFLayoutTextStripper() throws IOException {
            super();
        }

        @Override
        protected void writeString(String string, List<TextPosition> textPositions) throws IOException {
            Set<String> fonts = new HashSet<>();
            Set<String> colors = new HashSet<>();
            Set<Float> fontSizes = new HashSet<>();
            Set<Float> positions = new HashSet<>();

            for (TextPosition text : textPositions) {
                PDFont font = text.getFont();
                float fontSize = text.getFontSize();
                float x = text.getXDirAdj();
                float y = text.getYDirAdj();
                PDColor color = text.getGraphicsState().getNonStrokingColor();

                fonts.add(font.getName());
                fontSizes.add(fontSize);
                positions.add(x);
                positions.add(y);

                if (color != null && color.getColorSpace() instanceof PDDeviceRGB) {
                    float[] components = color.getComponents();
                    String rgb = String.format("#%02X%02X%02X", 
                            (int) (components[0] * 255), 
                            (int) (components[1] * 255), 
                            (int) (components[2] * 255));
                    colors.add(rgb);
                }
            }

            StringBuilder builder = new StringBuilder();
            builder.append("Text: ").append(string).append("\n");
            builder.append("Fonts: ").append(fonts).append("\n");
            builder.append("Font Sizes: ").append(fontSizes).append("\n");
            builder.append("Colors: ").append(colors).append("\n");
            builder.append("Positions: ").append(positions).append("\n");
            builder.append("-------------------------------------------------\n");

            super.writeString(builder.toString(), textPositions);
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java PdfLayoutAnalyzer <input-pdf-path> <output-report-path>");
            return;
        }
        analyzePdfLayout(args[0], args[1]);
    }
}