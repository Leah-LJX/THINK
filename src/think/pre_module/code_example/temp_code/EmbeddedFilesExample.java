import org.apache.pdfbox.pdmodel.PDDocumentNameDictionary;
import org.apache.pdfbox.pdmodel.common.PDEmbeddedFilesNameTreeNode;

public class EmbeddedFilesExample {
    public static void main(String[] args) {
        PDDocumentNameDictionary documentNameDictionary = new PDDocumentNameDictionary();
        
        PDEmbeddedFilesNameTreeNode embeddedFilesNode = documentNameDictionary.getEmbeddedFiles();
        
        // Other code using the embeddedFilesNode
    }
}