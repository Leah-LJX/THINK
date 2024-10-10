import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;

public class FontExample {
    public static void main(String[] args) {
        PDTrueTypeFont font = new PDTrueTypeFont();

        boolean embedded = font.isEmbedded();

        System.out.println("Font is embedded: " + embedded);
    }
}