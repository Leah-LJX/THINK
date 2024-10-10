import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDDocumentNameDictionary;
import org.apache.pdfbox.pdmodel.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.common.PDNameTreeNode;
import org.apache.pdfbox.pdmodel.common.PDNumberTreeNode;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PDFTableOfContentsExtractor {

    public static void extractTableOfContentsToJson(String pdfFilePath, String jsonOutputPath) {
        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
            PDDocumentOutline outline = document.getDocumentCatalog().getDocumentOutline();
            if (outline != null) {
                FileWriter writer = new FileWriter(jsonOutputPath);
                writer.write("{\n");
                writer.write("  \"tableOfContents\": [\n");
                writeOutlineItem(outline.getFirstChild(), writer, 1);
                writer.write("  ]\n");
                writer.write("}\n");
                writer.close();
            } else {
                System.out.println("No table of contents found in the PDF.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeOutlineItem(PDOutlineItem item, FileWriter writer, int level) throws IOException {
        if (item != null) {
            writer.write("    {\n");
            writer.write("      \"title\": \"" + item.getTitle() + "\",\n");
            writer.write("      \"page\": " + item.findDestinationPage(document) + "\n");
            if (item.getNextSibling() != null) {
                writer.write("    },\n");
                writeOutlineItem(item.getNextSibling(), writer, level);
            } else {
                writer.write("    }\n");
            }
            if (item.getFirstChild() != null) {
                writer.write(",\n");
                writeOutlineItem(item.getFirstChild(), writer, level + 1);
            }
        }
    }
}