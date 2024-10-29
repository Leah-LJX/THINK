import org.apache.pdfbox.io.RandomAccessStreamCache;

public class PDFMergerExample {

  public static void main(String[] args) {
    RandomAccessStreamCache.StreamCacheCreateFunction streamCacheCreateFunction = null;
    CompressParameters compressParameters = null;
  
    PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
    pdfMergerUtility.mergeDocuments(streamCacheCreateFunction, compressParameters);
  }
}