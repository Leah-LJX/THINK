import com.example.PhotometricInterpreterFloat;

public class ImageRenderer {
    public PhotometricInterpreterFloat interpreter;

    public ImageRenderer(PhotometricInterpreterFloat interpreter) {
        this.interpreter = interpreter;
    }

    public float renderImage() {
        // Rendering logic
        return interpreter.getMinFound();
    }
}