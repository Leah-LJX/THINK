import somePackage.AbstractMLModelWriter;

public class ModelPersistence {
    public AbstractMLModelWriter modelWriter;

    public ModelPersistence(AbstractMLModelWriter modelWriter) {
        this.modelWriter = modelWriter;
    }

    public void saveModel() {
        modelWriter.persist();
    }
}