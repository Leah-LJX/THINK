import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.chunker.MaxentModel;
import opennlp.tools.chunker.ChunkerFactory;

public class ChunkerModel {
    public String languageCode;
    public MaxentModel chunkerModel;
    public ChunkerFactory factory;

    public ChunkerModel(String languageCode, MaxentModel chunkerModel, ChunkerFactory factory) {
        this.languageCode = languageCode;
        this.chunkerModel = chunkerModel;
        this.factory = factory;
    }

    // Other methods can be added here
}