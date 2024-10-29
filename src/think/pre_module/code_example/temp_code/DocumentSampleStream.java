import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.util.FilterObjectStream;

public class DocumentSampleStream extends FilterObjectStream<String,DocumentSample> {

    public DocumentSampleStream(ObjectStream<String> samples) {
        super(samples);
    }

    // Other methods and functionality here
}