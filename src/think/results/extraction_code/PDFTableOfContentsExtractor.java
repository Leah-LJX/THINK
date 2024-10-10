import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineNode;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PDFTableOfContentsExtractor {

    public static void extractTableOfContentsToJson(String pdfFilePath, String jsonOutputPath) {
        PDDocument document = null;
        try {
            // Load the PDF document
            document = PDDocument.load(new File(pdfFilePath));
            
            // Extract the table of contents
            PDDocumentOutline outline = document.getDocumentCatalog().getDocumentOutline();
            if (outline != null) {
                List<Map<String, Object>> toc = new ArrayList<>();
                processOutlineItems(outline, toc, 0);
                
                // Convert to JSON and write to file
                ObjectMapper mapper = new ObjectMapper();
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonOutputPath), toc);
            } else {
                System.out.println("No table of contents found in the PDF.");
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

    public static void processOutlineItems(PDOutlineNode outlineNode, List<Map<String, Object>> toc, int level) throws IOException {
        PDOutlineItem current = outlineNode.getFirstChild();
        while (current != null) {
            Map<String, Object> item = new HashMap<>();
            item.put("title", current.getTitle());
            item.put("level", level);

            if (current.getDestination() instanceof PDPageDestination) {
                PDPageDestination pd = (PDPageDestination) current.getDestination();
                item.put("page", pd.retrievePageNumber() + 1); // Pages are zero-based in PDFBox
            }

            toc.add(item);

            // Process any children (sub-sections)
            if (current.hasChildren()) {
                processOutlineItems(current, toc, level + 1);
            }
            
            current = current.getNextSibling();
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: PDFTableOfContentsExtractor <pdf-file-path> <json-output-path>");
            return;
        }

        String pdfFilePath = args[0];
        String jsonOutputPath = args[1];
        extractTableOfContentsToJson(pdfFilePath, jsonOutputPath);
    }
}