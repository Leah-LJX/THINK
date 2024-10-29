import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PerceptronModelWriterExample {

    public PerceptronModelWriter modelWriter;

    public PerceptronModelWriterExample(PerceptronModelWriter modelWriter) {
        this.modelWriter = modelWriter;
    }

    public void persistModel() {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("perceptronModel.bin"))) {
            modelWriter.writeUTF("This is a perceptron model");
            modelWriter.writeDouble(0.75);
            modelWriter.writeInt(100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}