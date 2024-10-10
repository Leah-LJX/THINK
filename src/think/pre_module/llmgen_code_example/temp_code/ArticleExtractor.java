import org.apache.pdfbox.text.PDFTextStripper;

public class ArticleExtractor {
    public PDFTextStripper textStripper;

    public ArticleExtractor() {
        this.textStripper = new PDFTextStripper();
    }

    protected void startArticle() {
        textStripper.startArticle();
    }
}