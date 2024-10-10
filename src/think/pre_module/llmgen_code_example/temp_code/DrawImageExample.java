import org.apache.pdfbox.pdmodel.graphics.form.PDImageXObject;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.graphics.pattern.PDPatternResources;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.content.PDPageContentStream;

public class DrawImageExample {
    public static void main(String[] args) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            PDImageXObject image = new PDImageXObject(new COSDictionary());
            Matrix matrix = new Matrix();
            
            PDPatternContentStream patternContentStream = new PDPatternContentStream();
            patternContentStream.drawImage(image, matrix);
            
            contentStream.close();
            document.save("example.pdf");
            document.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}