import org.apache.pdfbox.text.PDFTextStripper;

public class PDFWriter {
    public PDFTextStripper textStripper;

    public PDFWriter() {
        this.textStripper = new PDFTextStripper();
    }

    public void writeEndOfParagraph(String text) {
        // Write something at the end of a paragraph
        textStripper.writeParagraphEnd();
        System.out.println("End of paragraph: " + text);
    }
}