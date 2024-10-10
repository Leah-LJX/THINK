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
                PDOutlineItem root = outline.getFirstChild();
                if (root != null) {
                    String json = convertOutlineItemToJson(root);
                    writeJsonToFile(json, jsonOutputPath);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String convertOutlineItemToJson(PDOutlineItem item) {
        StringBuilder json = new StringBuilder("{");
        json.append("\"title\": \"").append(item.getTitle()).append("\",");
        if (item.getDestination() != null) {
            json.append("\"page\": ").append(item.getDestination().retrievePageNumber() + 1).append(",");
        }
        if (item.getNextSibling() != null) {
            json.append("\"next\": ").append(convertOutlineItemToJson(item.getNextSibling())).append(",");
        }
        if (item.getFirstChild() != null) {
            json.append("\"children\": [").append(convertOutlineItemToJson(item.getFirstChild())).append("]");
        }
        json.append("}");
        return json.toString();
    }

    public static void writeJsonToFile(String json, String outputPath) {
        try (FileWriter writer = new FileWriter(outputPath)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}