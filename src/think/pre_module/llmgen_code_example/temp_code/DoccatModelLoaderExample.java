import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DoccatModelLoader;

public class DoccatModelLoaderExample {
    public static void main(String[] args) {
        DoccatModelLoader modelLoader = new DoccatModelLoader();

        DoccatModel model = modelLoader.loadModel();

        System.out.println("Model loaded successfully: " + model);
    }
}