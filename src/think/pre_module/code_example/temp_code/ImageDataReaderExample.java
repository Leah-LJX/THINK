import com.example.TiffDirectory;
import com.example.PhotometricInterpreter;

public class ImageDataReaderExample {

    public static void main(String[] args) {
        TiffDirectory directory = new TiffDirectory();
        PhotometricInterpreter photometricInterpreter = new PhotometricInterpreter();
        int[] bitsPerSample = {8, 8, 8};
        int predictor = 2;
        int samplesPerPixel = 3;
        int sampleFormat = 1;
        int width = 800;
        int height = 600;
        TiffPlanarConfiguration planarConfiguration = TiffPlanarConfiguration.CHUNKY;

        ImageDataReader imageDataReader = new ImageDataReader(directory, photometricInterpreter, bitsPerSample, predictor, samplesPerPixel, sampleFormat, width, height, planarConfiguration);
    }
}