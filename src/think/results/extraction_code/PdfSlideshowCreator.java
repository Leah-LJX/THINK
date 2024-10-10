import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionJavaScript;
import org.apache.pdfbox.pdmodel.PDPageTree;
import java.io.File;
import java.io.IOException;

public class PdfSlideshowCreator {

    public static void createPdfSlideshow(String[] pdfFilePaths, String slideshowOutputPath, int[] transitionTimes) throws IOException {
        if (pdfFilePaths == null || pdfFilePaths.length == 0 || slideshowOutputPath == null || transitionTimes == null || transitionTimes.length != pdfFilePaths.length) {
            throw new IllegalArgumentException("Invalid input parameters.");
        }

        try (PDDocument outputDocument = new PDDocument()) {
            for (int i = 0; i < pdfFilePaths.length; i++) {
                PDDocument inputDocument = PDDocument.load(new File(pdfFilePaths[i]));
                PDPageTree pages = inputDocument.getPages();

                for (PDPage page : pages) {
                    PDPage importedPage = outputDocument.importPage(page);
                    addTransition(importedPage, transitionTimes[i]);
                }

                inputDocument.close();
            }

            String javaScript = buildJavaScript(transitionTimes);
            PDActionJavaScript action = new PDActionJavaScript(javaScript);
            outputDocument.getDocumentCatalog().setOpenAction(action);

            outputDocument.save(slideshowOutputPath);
        }
    }

    public static void addTransition(PDPage page, int transitionTime) {
        // Transition time is in seconds
        String transitionScript = "this.setPageTransitions(0, " + transitionTime + ", " + transitionTime + ");";
        PDActionJavaScript js = new PDActionJavaScript(transitionScript);
        page.getCOSObject().setString("AA", js.getAction());
    }

    public static String buildJavaScript(int[] transitionTimes) {
        StringBuilder script = new StringBuilder();
        script.append("var pageCount = this.numPages;\n");
        script.append("var currentPage = 0;\n");

        for (int i = 0; i < transitionTimes.length; i++) {
            script.append("setTimeout(function() { this.pageNum = ").append(i + 1).append("; }, ")
                  .append(transitionTimes[i] * 1000).append(");\n");
        }

        script.append("this.pageNum = 1;\n");
        return script.toString();
    }

    public static void main(String[] args) {
        try {
            String[] pdfFiles = {"input1.pdf", "input2.pdf", "input3.pdf"};
            int[] transitionTimes = {5, 10, 15}; // transition times in seconds
            createPdfSlideshow(pdfFiles, "outputSlideshow.pdf", transitionTimes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}